package com.github.example.app;

import com.github.example.app.entity.Address;
import com.github.example.app.entity.Employee;
import com.github.example.app.entity.PhoneNumber;
import com.github.example.app.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AutoCommandLine implements CommandLineRunner {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {

        List<PhoneNumber> phoneNumbers = List.of(
                new PhoneNumber("Mobile", "123-456-7890"),
                new PhoneNumber("Work", "987-654-3210")
        );
        Address homeAddress = new Address("123 Main St", "New York", "NY", "10001");
        Address workAddress = new Address("123 Main St", "New York", "NY", "10001");
        Employee employee = new Employee("John Doe", homeAddress, workAddress, phoneNumbers);

        employeeRepository.save(employee);

        employeeRepository.findAll().forEach(emp ->
                System.out.println(
                        emp.getName() +
                                " - " +
                                emp.getHomeAddress().getCity() +
                                " - " +
                                emp.getWorkAddress().getCity() +
                                " - " +
                                emp.getPhoneNumbers()
                )
        );
    }
}
