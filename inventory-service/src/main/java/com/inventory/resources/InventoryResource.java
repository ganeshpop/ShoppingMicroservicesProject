package com.inventory.resources;

import com.inventory.bean.InventoryItem;
import com.inventory.bean.InventoryItemList;
import com.inventory.exceptions.ItemNotFoundException;
import com.inventory.service.InventoryServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;


@RestController
@CrossOrigin
@RequestMapping(path = "/inventories")
public class InventoryResource {

    private InventoryServiceInterface inventoryService;

    @Autowired
    public void setInventoryService(InventoryServiceInterface inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping(produces = "Application/json")
    InventoryItemList getAllInventoryItems() {
        return inventoryService.getAllInventoryItems();
    }

    @GetMapping(path = "/{id}", produces = "Application/json")
    InventoryItem getInventoryItemById(@PathVariable("id") Long id) {
        Optional<InventoryItem> inventoryItem = inventoryService.getInventoryItemById(id);
        if (!inventoryItem.isPresent()) {
            throw new ItemNotFoundException("No Item With ID " + id + " Found");
        }
        return inventoryItem.get();
    }

    @GetMapping(path = "/order/code/{productCode}", produces = "Application/json")
    InventoryItem getInventoryItemByProductCodeForOrder(@PathVariable("productCode") String productCode) {
        return inventoryService.getInventoryItemByProductCodeForOrder(productCode);
    }

    @GetMapping(path = "/code/{productCode}", produces = "Application/json")
    ResponseEntity<Object> getInventoryItemByProductCode(@PathVariable("productCode") String productCode) {
        Optional<InventoryItem> inventoryItem = inventoryService.getInventoryItemByProductCode(productCode);
        if(!inventoryItem.isPresent())
            throw new ItemNotFoundException("No Item With Product Code " + productCode + " Found");
        return ResponseEntity.status(HttpStatus.OK).body(inventoryItem.get());
    }

    @PostMapping(produces = "Application/json", consumes = "Application/json")
    ResponseEntity<Object> saveInventoryItem(@RequestBody InventoryItem inventoryItem) {
        InventoryItem inventoryItem1 = inventoryService.insertInventoryItem(inventoryItem);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(inventoryItem1.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/{id}", produces = "Application/json")
    ResponseEntity<Object> deleteInventoryItemById(@PathVariable("id") Long id) {
        InventoryItem inventoryItem = getInventoryItemById(id);
        inventoryService.deleteInventoryItem(inventoryItem);
        return ResponseEntity.noContent().build();
    }

    InventoryItem updateInventoryItemQuantityByProductCode(@PathVariable("productCode") String productCode, @PathVariable("quantity") Integer quantity) {
        Optional<InventoryItem> inventoryItem = inventoryService.updateInventoryItemQuantityByProductCode(productCode, quantity);
        if (!inventoryItem.isPresent()) {
            throw new ItemNotFoundException("No Item With Product Code " + productCode + " Found");
        }
        return inventoryItem.get();
    }

    @PutMapping(produces = "Application/json", consumes = "Application/json")
    InventoryItem updateInventoryItemQuantity(@RequestBody InventoryItem inventoryItem) {
        Optional<InventoryItem> inventoryItem1 = inventoryService.updateInventoryItemQuantity(inventoryItem);
        if (!inventoryItem1.isPresent()) {
            throw new ItemNotFoundException("No Item Not Found To Update");
        }
        return inventoryItem1.get();
    }
}