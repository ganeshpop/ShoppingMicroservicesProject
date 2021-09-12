package com.shopping.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Login {
    @NotBlank(message = "Password Cannot Be Blank")
    private String userName;
    @NotBlank(message = "Password Cannot Be Blank")
    private String password;
}
