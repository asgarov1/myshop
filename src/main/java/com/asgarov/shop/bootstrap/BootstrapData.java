package com.asgarov.shop.bootstrap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.asgarov.shop.entity.Admin;
import com.asgarov.shop.entity.Product;
import com.asgarov.shop.entity.User;
import com.asgarov.shop.service.AdminService;
import com.asgarov.shop.service.ProductService;
import com.asgarov.shop.service.UserService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private ProductService productService;
    private UserService userService;
    private AdminService adminService;

    public BootstrapData(
            final ProductService productService,
            final UserService userService,
            final AdminService adminService) {
        this.productService = productService;
        this.userService = userService;
        this.adminService = adminService;
    }

//    @Override public void onApplicationEvent(final ContextRefreshedEvent contextRefreshedEvent) {
//        init();
//    }

    @Override
    public void run(final String... args) {
        init();
    }

    private void init() {
        productService.saveAll(generateProducts(3));
        userService.save(new User("Max", "Musterman", "user@mail.ru", "pass", "Musterstrasse", "Musterstadt", "12345", "Germany"));
        userService.save(new User("Misha", "Mishkin", "misha@mail.ru", "pass", "Musterstrasse", "Musterstadt", "12345", "Germany"));
        userService.save(new User("Sveta", "Svetkin", "sveta@mail.ru", "pass", "Musterstrasse", "Musterstadt", "12345", "Germany"));
        userService.save(new User("Masha", "Mashkin", "masha@mail.ru", "pass", "Musterstrasse", "Musterstadt", "12345", "Germany"));

        adminService.save(new Admin("Admin", "Admini", "admin@mail.ru", "admin"));
    }

    private List<Product> generateProducts(final int numberOfProducts) {
        List<Product> products = new ArrayList<>();
        for (int i = 1; i <= numberOfProducts; i++) {
            products.add(
                    new Product("Product " + randomCharacters(5) + i, (int) (Math.random() * 100), "" + i + ".jpeg",
                            "blabla"));
        }
        return products;
    }

    private String randomCharacters(final int amount) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < amount; i++) {
            stringBuilder.append((char) (new Random().nextInt((90 - 65) + 1) + 65));
        }
        return stringBuilder.toString();
    }
}
