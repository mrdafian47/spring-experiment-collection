package com.github.example.app.service;

import com.github.example.app.entity.Order;
import com.github.example.app.event.OrderCreatedEvent;
import com.github.example.app.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    /**
     * `@Transactional` ensures that order is saved in a single database transaction.
     * eventPublisher.publishEvent() triggers the OrderCreatedEvent
     *
     * @param product name of product
     * @param amount  amount of product
     * @return result entity saved
     */
    @Transactional
    @Override
    public Order placeOrder(String product, double amount) {
        Order order = new Order();
        order.setProduct(product);
        order.setAmount(amount);

        // publish the event inside the transaction
        eventPublisher.publishEvent(new OrderCreatedEvent(order));

        return orderRepository.save(order);
    }
}
