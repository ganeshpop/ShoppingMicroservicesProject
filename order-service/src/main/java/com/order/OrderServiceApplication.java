package com.order;

import com.order.bean.OrderItem;
import com.order.bean.UserOrder;
import com.order.service.OrderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(scanBasePackages = "com.order")
@EnableJpaRepositories(basePackages = "com.order.persistence")
@EntityScan(basePackages = "com.order.bean")
@EnableEurekaClient
public class OrderServiceApplication implements CommandLineRunner {
    private OrderServiceInterface orderService;

    @Autowired
    public void setOrderService(OrderServiceInterface orderService) {
        this.orderService = orderService;
    }

    @Bean
    @LoadBalanced
    RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    @Override
    public void run(String... args) {
        List<OrderItem> items = new ArrayList<>();
        items.add(new OrderItem("P1001",1));
        items.add(new OrderItem("P1002",2));
        items.add(new OrderItem("P1004",3));
        items.add(new OrderItem("P1007",1));
        orderService.createOrder(new UserOrder("ravi", items));
        List<OrderItem> items1 = new ArrayList<>();
        items1.add(new OrderItem("P1001",1));
        items1.add(new OrderItem("P1004",2));
        items1.add(new OrderItem("P1007",1));
        orderService.createOrder(new UserOrder("ravi", items1));
    }
}
