package com.shopping.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopping.bean.*;
import com.shopping.service.CatalogService;
import com.shopping.service.InventoryService;
import com.shopping.service.OrderService;
import com.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("user")
public class ShoppingMenuController {
    CatalogService catalogService;
    InventoryService inventoryService;
    OrderService orderService;
    UserService userService;
    Cart cart;

    @Autowired
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Autowired
    public void setCatalogService(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @Autowired
    public void setInventoryService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    public User setUserSession(User user) {
        return user;
    }


    @RequestMapping("menu")
    public ModelAndView menuController(HttpSession session) {
        User user = (User) session.getAttribute("user");
        ModelAndView modelAndView = new ModelAndView("shoppingMenu", "user", user);
        modelAndView.addObject("lastOrder", orderService.getLastOrder(user.getName()));
        return modelAndView;
    }

    @RequestMapping("support")
    public ModelAndView supportController() {
        return new ModelAndView("shoppingSupport");
    }

    @RequestMapping("sessionSupport")
    public ModelAndView sessionSupportController() {
        return new ModelAndView("shoppingUserSupport");
    }

    @RequestMapping("getOrders")
    public ModelAndView orderController(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("shoppingOrders");
        User user = (User) session.getAttribute("user");
        UserOrderList userOrderList = orderService.getAllOrdersByUserName(user.getName());
        if (userOrderList != null) {
            modelAndView.addObject("orders", userOrderList.getOrders());
        }
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping("showCart")
    public ModelAndView showCartController() {
        ModelAndView modelAndView = new ModelAndView("shoppingCart");
        return getModelAndView(cart, modelAndView);
    }


    @RequestMapping("passwordChange")
    public ModelAndView passwordChangeController() {
        ModelAndView modelAndView = new ModelAndView("shoppingChangePassword");
        return modelAndView.addObject("password", new Password());

    }

    @RequestMapping("orderNow")
    public ModelAndView newOrderController() {
        ModelAndView modelAndView = new ModelAndView("shoppingOrderPage");
        List<Product> productList = catalogService.getAllProducts();
        List<InventoryItem> inventoryItemList = new ArrayList<>();
        for (Product product : productList) {
            inventoryItemList.add(inventoryService.getInventoryItemByCode(product.getCode()));
        }
        modelAndView.addObject("inventories", inventoryItemList);
        modelAndView.addObject("products", productList);
        return modelAndView;
    }


    @RequestMapping("viewOrder")
    public ModelAndView viewOrderController(@RequestParam("orderId") int orderId, HttpSession session) {
        UserOrder userOrder = orderService.getOrderById(orderId);
        User user = (User) session.getAttribute("user");
        ModelAndView modelAndView = new ModelAndView("shoppingOrderDetails");
        modelAndView.addObject("order", userOrder);
        modelAndView.addObject("user", user);
        ProductList productList = new ProductList();
        for (OrderItem item : userOrder.getItems()) {
            productList.getProducts().add(catalogService.getProductByCode(item.getProductCode()));
        }
        modelAndView.addObject("products", productList.getProducts());
        modelAndView.addObject("message", "Order Placed by " + userOrder.getUserName());

        return modelAndView;
    }

    @RequestMapping("addToCart")
    public ModelAndView addProductController(@RequestParam("code") String productCode) {
        int availableQuantity = inventoryService.getInventoryItemByCode(productCode).getAvailableQuantity();
        ModelAndView modelAndView = new ModelAndView("shoppingCart");
        if (cart.getCartItems().containsKey(productCode)) {
            int requestedQuantity = cart.getCartItems().get(productCode);
            if (requestedQuantity < availableQuantity) {
                cart.getCartItems().put(productCode, cart.getCartItems().get(productCode) + 1);
            } else
                return new ModelAndView("cartOperationOutput", "message", "You Are Requesting More Than Available Quantity");
        } else {
            if (availableQuantity > 0) cart.getCartItems().put(productCode, 1);
            else return new ModelAndView("cartOperationOutput", "message", "Product You Requested Is Out Of Stock");
        }
        return getModelAndView(cart, modelAndView);
    }

    @RequestMapping("removeFromCart")
    public ModelAndView removeProductController(@RequestParam("code") String productCode) {
        ModelAndView modelAndView = new ModelAndView("shoppingCart");
        if (cart.getCartItems().get(productCode) == 1) {
            cart.getCartItems().remove(productCode);
        } else cart.getCartItems().put(productCode, (cart.getCartItems().get(productCode) - 1));
        return getModelAndView(cart, modelAndView);
    }

    @RequestMapping("checkOut")
    public ModelAndView checkOutController(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("shoppingOrderDetails");
        User user = ((User) session.getAttribute("user"));
        List<OrderItem> orderItems = new ArrayList<>();
        for (String productCode : cart.getCartItems().keySet()) {
            orderItems.add(new OrderItem(productCode, cart.getCartItems().get(productCode)));
        }
        UserOrder newOrder;
        ObjectMapper mapper = new ObjectMapper();
        try {
            newOrder = mapper.readValue(orderService.saveOder(new UserOrder(user.getName(), orderItems)), UserOrder.class);
        } catch (IOException e) {
            newOrder = null;
        }
        if (newOrder != null) {
            this.cart = new Cart();
            ProductList productList = new ProductList();
            for (OrderItem item : newOrder.getItems()) {
                productList.getProducts().add(catalogService.getProductByCode(item.getProductCode()));
                inventoryService.updateInventoryItemQuantityByProductCode(item.getProductCode(), (inventoryService.getInventoryItemByCode(item.getProductCode()).getAvailableQuantity() - item.getQuantity()));
            }
            modelAndView.addObject("products", productList.getProducts());
            modelAndView.addObject("order", newOrder);
            modelAndView.addObject("message", "Thank You " + user.getName().toUpperCase() + " Order Successfully Placed");
            return modelAndView;
        }
        return new ModelAndView("cartOperationOutput", "message", "Failed to Place the Order, Please Try Again.");
    }

    private ModelAndView getModelAndView(Cart cart, ModelAndView modelAndView) {
        List<InventoryItem> inventoryItemList = new ArrayList<>();
        List<Product> selectedProductsList = new ArrayList<>();
        double cartFare = 0;
        int itemsInCart = 0;
        for (String productId : cart.getCartItems().keySet()) {
            Product product = catalogService.getProductByCode(productId);
            InventoryItem inventoryItem = inventoryService.getInventoryItemByCode(productId);
            inventoryItemList.add(inventoryItem);
            selectedProductsList.add(product);
            cartFare += product.getPrice() * cart.getCartItems().get(productId);
            itemsInCart += cart.getCartItems().get(productId);
        }
        modelAndView.addObject("inventories", inventoryItemList);
        modelAndView.addObject("products", selectedProductsList);
        modelAndView.addObject("cart", cart);
        modelAndView.addObject("cartFare", new BigDecimal(cartFare).setScale(2, RoundingMode.HALF_UP).doubleValue());
        modelAndView.addObject("itemsInCart", itemsInCart);
        return modelAndView;
    }


    @RequestMapping("changePassword")
    public ModelAndView passwordController(@Valid @ModelAttribute("password") Password password, BindingResult result, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("shoppingPasswordChangeOutput");
        if (result.hasErrors()) {
            return new ModelAndView("shoppingChangePassword", "command", new Password());
        }
        User user = (User) session.getAttribute("user");
        if (user.getPassword().equals(password.getOldPassword())) {
            if (password.getNewPasswordOne().equals(password.getNewPasswordTwo())) {
                userService.updateUserPasswordById(user.getId(), password.getNewPasswordOne());
                modelAndView.addObject("message", "Password Updated Successfully");
            } else modelAndView.addObject("message", "Passwords Didn't Match, Try Again");
        } else modelAndView.addObject("message", "You Have Entered a Wrong Password");
        return modelAndView;
    }


}
