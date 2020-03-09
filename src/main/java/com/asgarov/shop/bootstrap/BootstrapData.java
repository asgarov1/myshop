package com.asgarov.shop.bootstrap;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.asgarov.shop.entity.Admin;
import com.asgarov.shop.entity.Order;
import com.asgarov.shop.entity.Product;
import com.asgarov.shop.entity.User;
import com.asgarov.shop.service.AdminService;
import com.asgarov.shop.service.OrderService;
import com.asgarov.shop.service.ProductService;
import com.asgarov.shop.service.UserService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static com.asgarov.shop.util.FormHelper.getCountries;

@Component
public class BootstrapData implements CommandLineRunner {

    private ProductService productService;
    private UserService userService;
    private AdminService adminService;
    private OrderService orderService;

    public BootstrapData(
            final ProductService productService,
            final UserService userService,
            final AdminService adminService, final OrderService orderService) {
        this.productService = productService;
        this.userService = userService;
        this.adminService = adminService;
        this.orderService = orderService;
    }

    @Override
    public void run(final String... args) {
        init();
    }

    private void init() {
        productService.saveAll(generateProducts(3));

        orderService.save(Order.builder()
                .address("some street")
                .city("some city")
                .country(getCountries().entrySet().stream().findAny().get().getValue())
                .email("mishka@mail.ru")
                .firstName("Masha")
                .lastName("Mashkin")
                .localDate(LocalDate.now().minusDays(new Random().nextInt(15)).toString())
                .phoneNumber("123")
                .postalCode("ABC")
                .build());

        Order svetkinOrder = Order.builder()
                .address("some street")
                .city("some city")
                .country(getCountries().entrySet().stream().findAny().get().getValue())
                .email("sveta@mail.ru")
                .firstName("Sveta")
                .lastName("Svetkin")
                .localDate(LocalDate.now().minusDays(new Random().nextInt(15)).toString())
                .phoneNumber("123")
                .postalCode("ABC")
                .build();
        orderService.save(svetkinOrder);

        userService.save(new User("Max", "Musterman", "user@mail.ru", "pass", "Musterstrasse", "Musterstadt", "12345", "Germany"));
        userService.save(new User("Misha", "Mishkin", "misha@mail.ru", "pass", "Musterstrasse", "Musterstadt", "12345", "Germany"));

        User sveta = new User("Sveta", "Svetkin", "sveta@mail.ru", "pass", "Musterstrasse", "Musterstadt", "12345", "Germany");
        sveta.addOrder(svetkinOrder);
        userService.save(sveta);

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
