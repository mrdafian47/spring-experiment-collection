package com.github.example.app.service;

import com.github.example.app.entity.Order;

public interface OrderService {

    Order placeOrder(String product, double amount);
}
