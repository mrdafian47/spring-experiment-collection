package com.github.example.app.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "home_street")),
            @AttributeOverride(name = "city", column = @Column(name = "home_city")),
            @AttributeOverride(name = "state", column = @Column(name = "home_state")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "home_zip_code"))
    })
    private Address homeAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "work_street")),
            @AttributeOverride(name = "city", column = @Column(name = "work_city")),
            @AttributeOverride(name = "state", column = @Column(name = "work_state")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "work_zip_code"))
    })
    private Address workAddress;

    @ElementCollection
    @CollectionTable(name = "employee_phone_numbers", joinColumns = @JoinColumn(name = "employee_id"))
    private List<PhoneNumber> phoneNumbers;

    public Employee(String name, Address homeAddress, Address workAddress, List<PhoneNumber> phoneNumbers) {
        this.name = name;
        this.homeAddress = homeAddress;
        this.workAddress = workAddress;
        this.phoneNumbers = phoneNumbers;
    }
}
