package com.asgarov.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }

    //TODO:
    // 1. Confirmation for the product add
    // 2. Checkout page (Checkout page should have option to login as well as checkout as guest)
    // 3. Account page (Past orders to be shown on the account page)
    // 4. Give Admin ability to enter products in the API
}
