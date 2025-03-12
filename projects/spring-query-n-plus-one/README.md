# Spring Boot

## Handle Issue The N+1 Query Problem

When working with Spring Boot JPA, the N+1 query problem can impact performance due to lazy loading. 
To optimize queries and fetch related entities efficiently, JPA provides `@EntityGraph`, `@NamedEntityGraph`, and `JOIN FETCH`.

## Summary of Best Practices

| Approach            | Best For            | Pros                             | Cons                              |
|---------------------|---------------------|----------------------------------|-----------------------------------|
| `@EntityGraph`      | Select Queries      | Easy to use, reduces N+1 Problem | Works with method-based queries   |
| `@NamedEntityGraph` | Reusability         | Improves readability             | Requires entity-level definition  |
| `JOIN FETCH`        | Custom JPQL Queries | Full control over SQL            | Cannot be used in dynamic queries |

## Conclusion

* If using Spring Data JPA methods, prefer @EntityGraph.
* If using JPQL, use JOIN FETCH for optimized queries.
* If using repeated queries, define @NamedEntityGraph.

## Reference Link

https://www.knowledgefactory.net/2025/02/spring-boot-jpa-entitygraph-namedentitygraph-and-join-fetch--complete-guide-with-examples.html