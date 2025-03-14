package com.github.example.app;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
