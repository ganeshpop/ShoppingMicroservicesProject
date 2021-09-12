package com.inventory.service;


import com.inventory.bean.InventoryItem;
import com.inventory.bean.InventoryItemList;
import com.inventory.persistence.InventoryDaoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class InventoryService implements InventoryServiceInterface {
    private InventoryDaoInterface inventoryDoa;

    @Autowired
    public void setInventoryDoa(InventoryDaoInterface inventoryDoa) {
        this.inventoryDoa = inventoryDoa;
    }

    @Override
    public InventoryItemList getAllInventoryItems() {
        InventoryItemList inventoryItemList = new InventoryItemList();
        inventoryItemList.setInventoryItems(inventoryDoa.findAll());
        return inventoryItemList;
    }

    @Override
    public InventoryItem insertInventoryItem(InventoryItem inventoryItem) {
        return inventoryDoa.save(inventoryItem);
    }

    @Override
    public Optional<InventoryItem> getInventoryItemById(Long id) {
        return inventoryDoa.findById(id);
    }

    @Override
    public InventoryItem getInventoryItemByProductCodeForOrder(String productCode) {
        Optional<InventoryItem> inventoryItem = inventoryDoa.getInventoryItemByProductCode(productCode);
        return inventoryItem.orElse(null);
    }

    @Override
    public Optional<InventoryItem> getInventoryItemByProductCode(String productCode) {
        return inventoryDoa.getInventoryItemByProductCode(productCode);
    }

    @Override
    public void deleteInventoryItem(InventoryItem inventoryItem) {
        inventoryDoa.delete(inventoryItem);
    }


    @Override
    public Optional<InventoryItem> updateInventoryItemQuantityByProductCode(String productCode, int quantity) {
        if(inventoryDoa.updateInventoryItemQuantityByProductCode(productCode,quantity) > 0){
            return inventoryDoa.getInventoryItemByProductCode(productCode);
        }
        return Optional.empty();
    }

    @Override
    public Optional<InventoryItem> updateInventoryItemQuantity(InventoryItem inventoryItem) {
        if(inventoryDoa.updateInventoryItemQuantity(inventoryItem.getProductCode(), inventoryItem.getAvailableQuantity()) > 0){
            return inventoryDoa.getInventoryItemByProductCode(inventoryItem.getProductCode());
        }
        return Optional.empty();
    }
}