package com.order.service;


import com.order.bean.UserOrder;
import com.order.exception.InvalidProductQuantityException;

import java.util.List;
import java.util.Optional;

public interface OrderServiceInterface {

    Optional<UserOrder> findOrderById(Long id);
    List<UserOrder> getAllUserOrders();
    UserOrder createOrder(UserOrder orderTable) throws InvalidProductQuantityException;
    UserOrder getLastUserOrderByUserName(String userName);
    List<UserOrder> getUserOrdersByUserName(String userName);

}