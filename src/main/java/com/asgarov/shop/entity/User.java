package com.asgarov.shop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User extends Person {

    @Column
    private String phoneNumber;

    @Column
    private String address;

    @Column
    private String city;

    @Column
    private String postalCode;

    @Column
    private String country;

    @OneToOne
    private Cart cart;

    public User(
            final String firstName,
            final String lastName,
            final String email,
            final String password,
            final String address,
            final String city,
            final String postalCode, final String country) {
        super(firstName, lastName, email, password);
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }

    void setRole(){ role = Role.USER; }

    @Override public String toString() {
        return "User{" +
                "role=" + role +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                ", cart=" + cart +
                '}';
    }
}
