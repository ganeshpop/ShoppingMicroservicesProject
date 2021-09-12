package com.shopping.bean;


import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private int id;
    private String name;
    private String address;
    private String password;

    public User(String name, String address, String password) {
        this.name = name;
        this.password = password;
        this.address = address;
    }

    public User(int id, String password) {
        this.id = id;
        this.password = password;
    }


}
