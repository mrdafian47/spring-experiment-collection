package com.github.example.app.service;

import com.github.example.app.entity.Employee;
import com.github.example.app.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Revisions<Integer, Employee> getEmployeeRevisions(Long id) {
        return employeeRepository.findRevisions(id);
    }

    @Override
    public Revision<Integer, Employee> getLastRevision(Long id) {
        return employeeRepository.findLastChangeRevision(id).orElse(null);
    }
}
