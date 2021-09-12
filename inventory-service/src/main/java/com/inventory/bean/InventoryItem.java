

package com.inventory.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class InventoryItem {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Long id;
    private String productCode;
    private int availableQuantity;

    public InventoryItem(String productCode, int availableQuantity){
        this.productCode = productCode;
        this.availableQuantity = availableQuantity;
    }


}
