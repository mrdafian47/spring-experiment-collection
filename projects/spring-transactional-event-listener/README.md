# Spring Boot

## Listen Event from Transactional Event Listener

In Spring Data JPA, `@TransactionalEventListener` allows executing logic **only after a transaction is successfully committed**. 
This is useful for scenarios like:

* Sending emails after an order is placed
* Logging changes after database updates
* Publishing domain events

## Transactional Event Phases

| Phase              | Description                                                |
|--------------------|------------------------------------------------------------|
| `AFTER_COMMIT`     | Runs **only if** the transaction is committed successfully |
| `BEFORE_COMMIT`    | Runs **before** the transaction is committed               |
| `AFTER_ROLLBACK`   | Runs **only if** the transaction is rolled back            |
| `AFTER_COMPLETION` | Runs in **any case** commit or rollback                    |

## Summary of Best Practices

| Feature       | Details                                               |
|---------------|-------------------------------------------------------|
| Annotation    | `@TransactionalEventListener`                         |
| Use Case      | Post-commit actions like logging, notifications, etc  |
| Default Phase | `AFTER_COMMIT`                                        |
| Alternatives  | `BEFORE_COMMIT`, `AFTER_ROLLBACK`, `AFTER_COMPLETION` |

## Conclusion

* Use `AFTER_COMMIT` for actions like **email notifications and logging**.
* Avoid database writes in @TransactionalEventListener, as it **runs outside the original transaction**.
* Use `BEFORE_COMMIT` only if changes should be made before final commit.

## Reference Link

https://www.knowledgefactory.net/2025/02/spring-transactionaleventlistener-post-commit-actions-in-jpa--complete-guide-with-examples.html