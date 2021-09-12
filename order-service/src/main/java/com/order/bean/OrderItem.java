

package com.order.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;


@Getter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String productCode;
    private int quantity;
    private double productPrice;

    public OrderItem(String productCode, int quantity, double productPrice){
        this.productCode = productCode;
        this.quantity = quantity;
        this.productPrice = productPrice;
    }

    public OrderItem(String productCode, int quantity) {
        this.productCode = productCode;
        this.quantity = quantity;
    }
}
