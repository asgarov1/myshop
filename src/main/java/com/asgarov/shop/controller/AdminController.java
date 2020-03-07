package com.asgarov.shop.controller;

import javax.servlet.http.HttpServletRequest;

import com.asgarov.shop.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
    private UserService userService;

    public AdminController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String admin() {
        return "userPages/admin/index";
    }

    @PostMapping("/searchUsersBy")
    public String searchUsersBy(@RequestParam String searchParameter, @RequestParam String searchOption, HttpServletRequest request, Model model) {
        switch (searchOption.toLowerCase()){
            case "surname": model.addAttribute("results", userService.findAllByLastName(searchParameter)); break;
            case "email": model.addAttribute("results", userService.findAllByEmail(searchParameter)); break;
            case "phone": model.addAttribute("results", userService.findAllByPhone(searchParameter)); break;
        }
        return "userPages/admin/index";
    }

}
