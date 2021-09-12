

package com.order.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InventoryItem {
    private Long id;
    private String productCode;
    private int availableQuantity;

    public InventoryItem(String productCode, int availableQuantity){
        this.productCode = productCode;
        this.availableQuantity = availableQuantity;
    }


}
