package com.shopping.service;


import com.shopping.bean.User;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(name = "userDetails", fallbackMethod = "getUserByNameFallBack")
    public User getUserByName(String userName) {
        return restTemplate.getForObject("https://user-service/users/name/" + userName, User.class);
    }

    public void updateUserPasswordById(int id, String password) {
        User user = new User(id, password);
        restTemplate.put("https://user-service/users/changePassword/", user, User.class);
    }

    public void addUser(User user) {
        restTemplate.postForObject("https://user-service/users/", user, User.class);
    }

    public User getUserByNameFallBack(String userName, Exception e) {
        return new User(userName, "NA", "Na");
    }

}

