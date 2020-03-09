package com.asgarov.shop.entity;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Admin extends Person {

    public Admin(
            @NotNull @NotEmpty(message = "{error.empty}") final String firstName,
            @NotNull @NotEmpty(message = "{error.empty}") final String lastName,
            @NotNull @NotEmpty(message = "{error.empty}") @Email final String email,
            @NotNull @NotEmpty(message = "{error.empty}") final String password) {
        super(firstName, lastName, email, password);
    }

    void setRole() { role = Role.ADMIN; }

    @Override public String toString() {
        return "Admin{" +
                "role=" + role +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
