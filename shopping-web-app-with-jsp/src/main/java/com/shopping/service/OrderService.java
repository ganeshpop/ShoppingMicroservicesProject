package com.shopping.service;

import com.shopping.bean.OrderItem;
import com.shopping.bean.UserOrder;
import com.shopping.bean.UserOrderList;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(name = "orderDetails", fallbackMethod = "getAllOrdersByUserNameFallBack")
    public UserOrderList getAllOrdersByUserName(String name) {
        return restTemplate.getForObject("https://order-service/orders/name/" + name, UserOrderList.class);
    }

    public URL saveOder(UserOrder userOrder) {
        try {
            URI uri = restTemplate.postForLocation("https://order-service/orders/", userOrder);
            assert uri != null;
            return uri.toURL();
        } catch (MalformedURLException | HttpClientErrorException exception) {
            return null;
        }
    }

    @CircuitBreaker(name = "orderDetails", fallbackMethod = "getOrderFallBack")
    public UserOrder getLastOrder(String name) {
        return restTemplate.getForObject("https://order-service/orders/name/last/" + name, UserOrder.class);
    }

    @CircuitBreaker(name = "orderDetails", fallbackMethod = "getOrderFallBack")
    public UserOrder getOrderById(int id) {
        return restTemplate.getForObject("https://order-service/orders/" + id, UserOrder.class);
    }

    public UserOrderList getAllOrdersByUserNameFallBack(Exception e) {
        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(new OrderItem(0L, "NA", 0, 0));
        UserOrder userOrder = new UserOrder("NA", orderItemList);
        List<UserOrder> userOrderList = new ArrayList<>();
        userOrderList.add(userOrder);
        return new UserOrderList(userOrderList);
    }


    public UserOrder getOrderFallBack(Exception e) {
        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(new OrderItem(0L, "NA", 0, 0));
        return new UserOrder("NA", orderItemList);
    }
}
