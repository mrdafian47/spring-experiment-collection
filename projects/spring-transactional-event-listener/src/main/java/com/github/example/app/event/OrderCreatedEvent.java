package com.github.example.app.event;

import com.github.example.app.entity.Order;

public record OrderCreatedEvent(Order order) {
}
