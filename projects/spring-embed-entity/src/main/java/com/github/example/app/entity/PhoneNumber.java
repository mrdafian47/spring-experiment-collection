package com.github.example.app.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@Embeddable
public class PhoneNumber {
    private String type;
    private String number;
}
