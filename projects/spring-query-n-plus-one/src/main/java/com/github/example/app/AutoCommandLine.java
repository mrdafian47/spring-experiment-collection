package com.github.example.app;

import com.github.example.app.entity.Department;
import com.github.example.app.entity.Employee;
import com.github.example.app.repository.DepartmentRepository;
import com.github.example.app.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class AutoCommandLine implements CommandLineRunner {

    private static final Map<String, List<String>> departmentEmployeeMap = Map.of(
            "Marketing", List.of("Samuel", "Edward", "Stefani"),
            "Sales", List.of("Michael", "Roberto", "Joseph"),
            "Legal", List.of("Gaby", "George")
    );

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) {
        if (isDataEmpty()) {
            List<Department> departmentList = new ArrayList<>();
            departmentEmployeeMap.forEach((key, valueList) -> {

                List<Employee> employeeList = new ArrayList<>();
                Department department = new Department();
                department.setName(key);

                valueList.forEach(value -> {
                    Employee employee = new Employee();
                    employee.setName(value);
                    employeeList.add(employee);
                });

                department.setEmployees(employeeList);
                departmentList.add(department);
            });

            departmentRepository.saveAll(departmentList);
        }
    }

    private boolean isDataEmpty() {
        return isDataDepartmentEmpty() && isDataEmployeeEmpty();
    }

    private boolean isDataDepartmentEmpty() {
        return departmentRepository.findAll().isEmpty();
    }

    private boolean isDataEmployeeEmpty() {
        return employeeRepository.findAll().isEmpty();
    }
}
