package com.github.example.app.controller;

import com.github.example.app.entity.Order;
import com.github.example.app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    public OrderService orderService;

    @PostMapping
    public Order placeOrder(@RequestParam String product, @RequestParam double amount) {
        return orderService.placeOrder(product, amount);
    }
}
