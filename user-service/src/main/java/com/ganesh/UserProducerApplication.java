package com.ganesh;

import com.ganesh.bean.User;
import com.ganesh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;


@SpringBootApplication(scanBasePackages = "com.ganesh")
@EnableJpaRepositories(basePackages = "com.ganesh.persistence")
@EntityScan(basePackages = "com.ganesh.bean")
@EnableEurekaClient
@CrossOrigin
public class UserProducerApplication implements CommandLineRunner {
    private UserService userService;


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(UserProducerApplication.class, args);
    }

    @Override
    public void run(String... args)  {
        userService.insertUser(new User(1, "Ganesh", "Visakhapatnam, India", "password"));
        userService.insertUser(new User(2, "Rajesh", "Delhi, India", "password"));
        userService.insertUser(new User(3, "Ben", "Mumbai, India", "password"));
        userService.insertUser(new User(4, "Oman", "Hyderabad, India", "password"));
        userService.insertUser(new User(5, "Hari", "Visakhapatnam, India", "password"));
        userService.insertUser(new User(6, "Ravi", "Mumbai, India", "password"));
    }
}
