package com.github.example.app.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@Embeddable
public class Address {
    private String street;
    private String city;
    private String state;
    private String zipCode;
}
