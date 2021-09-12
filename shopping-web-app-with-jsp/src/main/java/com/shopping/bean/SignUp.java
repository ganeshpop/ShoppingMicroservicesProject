package com.shopping.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUp {
    @NotBlank(message = "Name Cannot Be Blank")
    private String userName;
    @Size(min = 5, message = "Address Should Have Minimum 5 Characters")
    private String address;
    @Size(min = 5, message = "Password Should Have Minimum 5 Characters")
    private String passwordOne;
    @Size(min = 5, message = "Password Should Have Minimum 5 Characters")
    private String passwordTwo;

}
