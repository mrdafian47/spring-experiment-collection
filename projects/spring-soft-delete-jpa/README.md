# Spring Boot

## Handle Soft Delete

In many applications, deleting a record permanently isn’t ideal. 
You might need to retain historical data or allow users to restore deleted records. 
That’s where soft deletes come in. 
Instead of removing data from the database, you mark it as deleted and filter it out from queries.

## Solution 1: Create Hibernate Event Listener

* This will automatically enable the deletedFilter for every session.
* Works seamlessly with repository.findAll(), so you don’t have to explicitly enable filters.

```java
@Component
public class HibernateFilterConfiguration {
    
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @PostConstruct
    public void configureFilters() {
        entityManagerFactory.unwrap(SessionFactory.class)
                .addSessionFactoryObserver(sessionFactory -> {
                    sessionFactory.withOptions().eventListenerRegistration().register(
                        new LoadEventListener() {
                            @Override
                            public void onLoad(LoadEvent event, LoadEventListener.LoadType loadType) {
                                Session session = event.getSession();
                                if (!session.isClosed()) {
                                    session.enableFilter("deletedFilter").setParameter("isDeleted", false);
                                }
                            }
                        }
                    );
                });
    }
}

```

## Solution 2: Use @Filter in a @Component Bean

* This ensures that every Hibernate session enables the filter.
* Works transparently when calling repository.findAll().

```java
@Component
public class HibernateInterceptor {
    
    @Autowired
    private EntityManager entityManager;

    @PostConstruct
    public void enableFilters() {
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("deletedFilter").setParameter("isDeleted", false);
    }
}

```

## Solution 3: Using @Query in Repository

* This is simpler but requires you to manually define queries for each method.

```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.deleted = false")
    List<User> findAllActiveUsers();
}

```

## Different Approach

| Approach                     | Pros                                    | Cons                                              |
|------------------------------|-----------------------------------------|---------------------------------------------------|
| Hibernate Event Listener     | Fully automatic, applies to all queries | More complex setup                                |
| Spring Bean to Enable Filter | Automatic, easy to implement            | Might not apply to every session in certain cases |
| Repository-based `@Query`    | Simple, works well for JPA              | Must be manually defined for each query           |


## Conclusion

* Data is never permanently deleted, making it recoverable.
* Deleted records are automatically filtered out in queries.
* You can restore or analyze historical data easily.

## Reference Link

https://www.knowledgefactory.net/2025/02/spring-boot-jpa-entitygraph-namedentitygraph-and-join-fetch--complete-guide-with-examples.html