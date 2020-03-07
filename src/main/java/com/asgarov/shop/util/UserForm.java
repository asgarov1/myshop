package com.asgarov.shop.util;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.asgarov.shop.validation.FieldMatch;

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
@FieldMatch(first = "confirmPassword", second = "password", message = "The password fields must match")
public class UserForm{

    @NotNull
    @NotEmpty(message="{registerForm.error.empty}")
    private String firstName;

    @NotNull
    @NotEmpty(message="{registerForm.error.empty}")
    private String lastName;

    @NotNull
    @NotEmpty(message="{registerForm.error.empty}")
    @Email
    private String email; //also username

    @NotNull
    @NotEmpty(message="{registerForm.error.empty}")
    private String password;

    @NotNull
    private String confirmPassword;

    @NotNull
    @NotEmpty(message="{registerForm.error.empty}")
    private String address;

    @NotNull
    @NotEmpty(message="{registerForm.error.empty}")
    private String city;

    @NotNull
    @NotEmpty(message="{registerForm.error.empty}")
    private String postalCode;

    @NotNull
    @NotEmpty(message="{registerForm.error.empty}")
    private String country;
}
