package com.inventory.service;

import com.inventory.bean.InventoryItem;
import com.inventory.bean.InventoryItemList;

import java.util.Optional;


public interface InventoryServiceInterface {

    InventoryItemList getAllInventoryItems();
    InventoryItem insertInventoryItem(InventoryItem inventoryItem);
    Optional<InventoryItem> getInventoryItemById(Long id);
    InventoryItem getInventoryItemByProductCodeForOrder(String productCode);
    Optional<InventoryItem> getInventoryItemByProductCode(String productCode);
    void deleteInventoryItem(InventoryItem inventoryItem);
    Optional<InventoryItem> updateInventoryItemQuantityByProductCode(String productCode, int quantity);
    Optional<InventoryItem> updateInventoryItemQuantity(InventoryItem inventoryItem);

}