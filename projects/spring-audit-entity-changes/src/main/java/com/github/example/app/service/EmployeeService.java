package com.github.example.app.service;

import com.github.example.app.entity.Employee;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Employee saveEmployee(Employee employee);

    Revisions<Integer, Employee> getEmployeeRevisions(Long id);

    Revision<Integer, Employee> getLastRevision(Long id);
}
