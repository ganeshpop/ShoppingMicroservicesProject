package com.shopping.controller;

import com.shopping.bean.Login;
import com.shopping.bean.SignUp;
import com.shopping.bean.User;
import com.shopping.service.OrderService;
import com.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
@SessionAttributes({"user"})
public class ShoppingSessionController {
    UserService userService;
    OrderService orderService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @ModelAttribute("user")
    public User setUserSession(User user) {
        return user;
    }

    @RequestMapping("login")
    public ModelAndView loginController() {
        return new ModelAndView("shoppingLogin", "login", new Login());
    }

    @RequestMapping("signup")
    public ModelAndView signupController() {
        return new ModelAndView("shoppingSignUp", "signup", new SignUp());
    }

    @RequestMapping("logout")
    public ModelAndView logoutController(HttpSession session) {
        session.invalidate();
        return new ModelAndView("shoppingLogin", "login", new Login());
    }


    @RequestMapping("verifyLogin")
    public ModelAndView verifyController(@Valid @ModelAttribute("login") Login login, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            return new ModelAndView("shoppingLogin", "command", new Login());
        }
        User user = userService.getUserByName(login.getUserName());
        if (user != null) {
            if (user.getPassword().equals(login.getPassword())) {
                modelAndView.addObject("message", "You Are Now Logged In");
                modelAndView.addObject("user", user);
                modelAndView.addObject("lastOrder", orderService.getLastOrder(user.getName()));
                modelAndView.setViewName("shoppingMenu");
                setUserSession(userService.getUserByName(user.getName()));
                return modelAndView;
            } else return new ModelAndView("shoppingLoginOutput", "message", "Invalid Password, Try Again");
        } else return new ModelAndView("shoppingLoginOutput", "message", "Invalid  Username");

    }


    @RequestMapping("createUser")
    public ModelAndView createController(@Valid @ModelAttribute("signup") SignUp signUp, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            return new ModelAndView("shoppingSignUp", "command", new SignUp());
        }
        if (signUp.getPasswordOne().equals(signUp.getPasswordTwo())) {
            User user = userService.getUserByName(signUp.getUserName());
            if (user == null) {
                userService.addUser(new User(signUp.getUserName(), signUp.getAddress(), signUp.getPasswordOne()));
                User newUser = userService.getUserByName(signUp.getUserName());
                if (newUser != null) {
                    modelAndView.addObject("message", "Congratulations, " + signUp.getUserName() + " Your Account is Created. Lets Do Some Shopping!");
                    modelAndView.addObject("user", newUser);
                    setUserSession(userService.getUserByName(newUser.getName()));
                    modelAndView.setViewName("shoppingMenu");
                    return modelAndView;
                } else return new ModelAndView("createUserOutput", "message", "SignUp Failed");
            } else return new ModelAndView("createUserOutput", "message", "User Name Already Taken");
        } else return new ModelAndView("createUserOutput", "message", "Passwords Didnt Match, Try Again");
    }


}
