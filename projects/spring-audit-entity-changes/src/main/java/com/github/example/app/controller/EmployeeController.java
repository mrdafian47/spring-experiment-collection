package com.github.example.app.controller;

import com.github.example.app.entity.Employee;
import com.github.example.app.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}/revisions")
    public Revisions<Integer, Employee> getEmployeeRevisions(@PathVariable Long id) {
        return employeeService.getEmployeeRevisions(id);
    }

    @GetMapping("/{id}/last-revision")
    public Revision<Integer, Employee> getLastRevision(@PathVariable Long id) {
        return employeeService.getLastRevision(id);
    }
}
