package com.order.service;


import com.order.bean.InventoryItem;
import com.order.bean.OrderItem;
import com.order.bean.Product;
import com.order.bean.UserOrder;
import com.order.exception.InvalidProductQuantityException;
import com.order.exception.ProductNotFoundException;
import com.order.exception.ProductNotFoundInInventoryException;
import com.order.persistence.OrderDaoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class OrderService implements OrderServiceInterface {
    private OrderDaoInterface orderDao;
    private InventoryService inventoryService;
    private CatalogService catalogService;

    @Autowired
    public void setCatalogService(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @Autowired
    public void setInventoryService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @Autowired
    public void setOrderDao(OrderDaoInterface orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public Optional<UserOrder> findOrderById(Long id) {
        return orderDao.findById(id);
    }

    @Override
    public List<UserOrder> getAllUserOrders() {
        return orderDao.findAll();
    }

    @Override
    public UserOrder getLastUserOrderByUserName(String userName) {
         List<UserOrder> userOrderList =  orderDao.getUserOrderByUserNameOrderByIdDesc(userName);
         if(!userOrderList.isEmpty()){
             return userOrderList.get(0);
         }
         return null;
    }

    @Override
    public List<UserOrder> getUserOrdersByUserName(String userName) {
        return orderDao.getUserOrdersByUserName(userName);
    }

    @Override
    public UserOrder createOrder(UserOrder userOrder) {
        List<OrderItem> items = userOrder.getItems();
        List<OrderItem> validItems = new ArrayList<>();
        int itemCount = 0;

        for (OrderItem orderItem : items) {
            InventoryItem inventoryItem = inventoryService.getInventoryItemByProductCode(orderItem.getProductCode());
            if (inventoryItem.getId() != 0) {
                if (orderItem.getQuantity() <= inventoryItem.getAvailableQuantity()) {
                    Product product = catalogService.getProductByProductCode(orderItem.getProductCode());
                    if (product.getId() != 0) {
                        orderItem.setProductPrice(BigDecimal.valueOf(product.getPrice() * orderItem.getQuantity()).setScale(2, RoundingMode.HALF_UP).doubleValue());
                        userOrder.setTotalFare(userOrder.getTotalFare() + orderItem.getProductPrice());
                        validItems.add(orderItem);
                        itemCount += orderItem.getQuantity();
                    } else {
                        throw new ProductNotFoundException("Product With Product Code " + orderItem.getProductCode() + " Not Found");
                    }
                } else {
                    throw new InvalidProductQuantityException("Requested Quantity [" + orderItem.getQuantity() + "] of Product " + orderItem.getProductCode() + " is More Than Available Quantity [" + inventoryItem.getAvailableQuantity() + "]");
                }
            } else {
                throw new ProductNotFoundInInventoryException("Product With Product Code " + orderItem.getProductCode() + " Not Found In Inventory ");
            }
        }
        userOrder.setItems(validItems);
        userOrder.setItemCount(itemCount);
        return orderDao.save(userOrder);
    }
}