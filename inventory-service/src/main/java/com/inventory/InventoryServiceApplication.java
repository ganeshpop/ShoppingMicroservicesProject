package com.inventory;

import com.inventory.bean.InventoryItem;
import com.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.inventory")
@EnableJpaRepositories(basePackages = "com.inventory.persistence")
@EntityScan(basePackages = "com.inventory.bean")
@EnableEurekaClient
public class InventoryServiceApplication implements CommandLineRunner {
    private InventoryService inventoryService;

    @Autowired
    public void setInventoryService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Override
    public void run(String... args)  {
        inventoryService.insertInventoryItem(new InventoryItem("P1001",5));
        inventoryService.insertInventoryItem(new InventoryItem("P1002",6));
        inventoryService.insertInventoryItem(new InventoryItem("P1003",5));
        inventoryService.insertInventoryItem(new InventoryItem("P1004",7));
        inventoryService.insertInventoryItem(new InventoryItem("P1005",4));
        inventoryService.insertInventoryItem(new InventoryItem("P1006",6));
        inventoryService.insertInventoryItem(new InventoryItem("P1007",5));
        inventoryService.insertInventoryItem(new InventoryItem("P1008",4));

    }
}
