package com.github.example.app.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@Entity
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET deleted = true WHERE id = ?") // Custom Delete Query
@Where(clause = "deleted = false") // Automatically exclude deleted record
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean deleted = false; // Soft delete flag

    /**
     * Ensures that even if an entity is deleted manually (without JPA’s delete() method), it is soft deleted properly.
     */
    @PreRemove
    public void preRemove() {
        this.deleted = true;
    }
}
