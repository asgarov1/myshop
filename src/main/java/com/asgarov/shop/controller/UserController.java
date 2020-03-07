package com.asgarov.shop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.asgarov.shop.entity.Cart;
import com.asgarov.shop.entity.Person;
import com.asgarov.shop.entity.Product;
import com.asgarov.shop.service.AdminService;
import com.asgarov.shop.service.UserService;
import com.asgarov.shop.util.UserForm;
import com.asgarov.shop.validation.UserFormValidator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import static com.asgarov.shop.util.FormHelper.getCountries;

@Controller
public class UserController {

    private UserService userService;
    private AdminService adminService;
    private UserFormValidator userValidator;
    private Cart shoppingCart;

    public UserController(
            final UserService userService,
            final AdminService adminService,
            final UserFormValidator userValidator, final Cart shoppingCart) {
        this.userService = userService;
        this.adminService = adminService;
        this.userValidator = userValidator;
        this.shoppingCart = shoppingCart;
    }

    @GetMapping("/addUser")
    public String newUser(Model model) {
        model.addAttribute("userForm", new UserForm());
        model.addAttribute("countries", getCountries());
        return "registerForm";
    }

    @PostMapping("/addUser")
    public String saveUser(@Valid UserForm userForm, BindingResult bindingResult, Model model){
        userValidator.validate(userForm, bindingResult);
        if(bindingResult.hasErrors()){
            model.addAttribute("emailExistsError", true);
            return "registerForm";
        }

        userService.save(userForm);
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public void logout(Model model) {
        model.addAttribute("user", null);
    }

    @ModelAttribute("productsInCart")
    public Map<Product, Integer> productsInCart() {
        return shoppingCart.getProductsInCart();
    }

    @ModelAttribute("total")
    public Integer total() {
        return shoppingCart.calculateTotal();
    }

//    For development - delete later
    @GetMapping("/users")
    public String showUsers(Model model) {
        List<Person> users = new ArrayList<>();
        users.addAll(userService.findAll());
        users.addAll(adminService.findAll());

        model.addAttribute("entities", users);
        return "forDebugging/showThem";
    }
}
