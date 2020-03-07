package com.asgarov.shop.util;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderForm {

    @NotEmpty(message="{registerForm.error.empty}")
    private String firstName;

    private String lastName;

    @NotEmpty(message="{registerForm.error.empty}")
    @Email
    private String email; //also username

    private String phoneNumber;

    private String address;

    private String city;

    private String postalCode;

    private String country;
}
