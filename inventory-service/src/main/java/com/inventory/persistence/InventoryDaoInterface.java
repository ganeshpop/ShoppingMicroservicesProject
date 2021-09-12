package com.inventory.persistence;


import com.inventory.bean.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface InventoryDaoInterface extends JpaRepository<InventoryItem, Integer> {
    Optional<InventoryItem> getInventoryItemByProductCode(String productCode);

    @Transactional
    @Modifying
    @Query("UPDATE InventoryItem SET availableQuantity =:quantity WHERE productCode =:productCode")
    int updateInventoryItemQuantityByProductCode(@Param("productCode") String productCode, @Param("quantity") Integer quantity);

    @Transactional
    @Modifying
    @Query("UPDATE InventoryItem SET availableQuantity =:quantity WHERE productCode =:productCode")
    int updateInventoryItemQuantity(@Param("productCode") String productCode, @Param("quantity") Integer quantity);


    Optional<InventoryItem> findById(Long id);
    InventoryItem deleteInventoryItemById(Long id);
}