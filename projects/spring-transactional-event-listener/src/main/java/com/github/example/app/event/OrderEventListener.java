package com.github.example.app.event;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class OrderEventListener {

    /**
     * `@TransactionalEventListener` ensures the listener runs only after the transaction is committed.
     * TransactionPhase.AFTER_COMMIT makes sure the event is handled only if the transaction is successful.
     *
     * @param event triggered event
     */
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleOrderCreated(OrderCreatedEvent event) {
        System.out.println("order created successfully: " + event.order().getProduct());
    }
}
