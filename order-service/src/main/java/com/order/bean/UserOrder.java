package com.order.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String userName;
    private int itemCount;
    private double totalFare;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> items;


    public UserOrder(String userName, List<OrderItem> items){
        this.userName = userName;
        this.items = items;
    }
}
