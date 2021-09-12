package com.shopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ShoppingApplicationConfigurationServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingApplicationConfigurationServerApplication.class, args);
    }

}
