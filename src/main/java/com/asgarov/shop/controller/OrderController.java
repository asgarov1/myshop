package com.asgarov.shop.controller;

import com.asgarov.shop.service.OrderService;
import com.asgarov.shop.util.OrderForm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderController {

    private OrderService orderService;

    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/placeOrder")
    public String placeOrder(OrderForm orderForm){
        orderService.placeOrder(orderForm);
        return "orderPlacementConfirmation";
    }


}
