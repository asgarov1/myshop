package com.asgarov.shop.controller;

import javax.servlet.http.HttpServletRequest;

import com.asgarov.shop.service.OrderService;
import com.asgarov.shop.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
    private UserService userService;
    private OrderService orderService;

    public AdminController(final UserService userService, final OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin/index";
    }

    @PostMapping("/searchUsersBy")
    public String searchUsersBy(@RequestParam String searchParameter, @RequestParam String searchOption,
           Model model) {
        switch (searchOption.toLowerCase()){
            case "surname": model.addAttribute("userResults", userService.findAllByLastName(searchParameter)); break;
            case "email": model.addAttribute("userResults", userService.findAllByEmail(searchParameter)); break;
            case "phone": model.addAttribute("userResults", userService.findAllByPhone(searchParameter)); break;
            default: model.addAttribute("userResults", userService.findAllByLastNamePhoneEmail(searchParameter)); break;
        }
        return "admin/index";
    }

    @PostMapping("/searchOrdersBy")
    public String searchOrdersBy(@RequestParam String searchParameter, @RequestParam String searchOption, Model model) {
        switch (searchOption.toLowerCase()){
        case "id": model.addAttribute("orders", orderService.findAllById(searchParameter)); break;
        case "date": model.addAttribute("orders", orderService.findAllByDate(searchParameter)); break;
        case "surname": model.addAttribute("orders", orderService.findAllByLastName(searchParameter)); break;
        default: model.addAttribute("orders", orderService.findAllByIdDateLastName(searchParameter)); break;
        }
        return "admin/index";
    }

}
