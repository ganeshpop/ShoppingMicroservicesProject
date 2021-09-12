package com.shopping.bean;

import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserOrder {
    private long id;
    private String userName;
    private int itemCount;
    private double totalFare;
    private List<OrderItem> items;


    public UserOrder(String name, List<OrderItem> items){
        this.userName = name;
        this.items = items;
    }
}
