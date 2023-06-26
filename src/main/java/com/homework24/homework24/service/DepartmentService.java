package com.homework24.homework24.service;

import com.homework24.homework24.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Employee getEmployeeWithMaxSalary(int department) {
        return employeeService.getAll().stream()
                .filter(e ->e.getDepartment() == department)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElse(null);
    }

    public Employee getEmployeeWithMinSalary(int department) {
        return employeeService.getAll().stream()
                .filter(e ->e.getDepartment() == department)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElse(null);
    }

    public List<Employee> getEmployeeByDepartment(int department) {
        return employeeService.getAll().stream()
                .filter(e->e.getDepartment() == department)
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Employee>> getEmployeeGrouped() {
        return employeeService.getAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
