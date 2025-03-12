# Spring Boot

## Using @Embeddable, @Embedded and @ElementCollection

Spring Data JPA allows you to model complex entities using @Embeddable objects and Embedded Collections, reducing
redundancy and making the design cleaner.

## Understanding @Embeddable and @Embedded

* `@Embeddable`: Defines a class whose properties can be embedded in an entity.
* `@Embedded`: Used in an entity to embed an `@Embeddable` object.
* Helps achieve component mapping and promotes code reusability.

## When to Use @Embeddable vs @OneToMany?

| Feature              | `@Embeddable` + `@ElementCollection` | `@OneToMany` ( Separate Entity ) |
|----------------------|--------------------------------------|----------------------------------|
| Separate Table       | No ( Except for Collections )        | Yes                              |
| Foreign Key Needed   | No                                   | Yes                              |
| Performance Overhead | Lower                                | Higher                           |
| Query Flexibility    | Lower                                | Higher                           |

## Conclusion

* `@Embeddable` is useful for reusable, value-type components.
* `@ElementCollection` is for multiple embedded objects in a separate table.
* Use @OneToMany for full lifecycle-managed entities.

## Reference Link

https://www.knowledgefactory.net/2025/03/spring-data-jpa-using-embeddable-embedded-and-elementcollection.html