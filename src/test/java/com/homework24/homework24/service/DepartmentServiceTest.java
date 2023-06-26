package com.homework24.homework24.service;

import com.homework24.homework24.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    @Mock
    EmployeeService employeeService;

    @InjectMocks
    DepartmentService departmentService;

//    void init(){
//        EmployeeService mock = Mockito.mock(EmployeeService.class);
//        DepartmentService departmentService1 = new DepartmentService(mock);
//    }

    List<Employee> employees = Arrays.asList(
                            new Employee("Ivano", "Ivanov", 3, 10000.0),
                            new Employee("Roman", "Ivanov", 3, 10000.0),
                            new Employee("Max", "Ivanov", 1, 10000.0)

    );
    @BeforeEach
    void setup(){
        when(employeeService.getAll()).thenReturn(employees);
    }
    @Test
    void test(){
        Employee e = new Employee("Max", "Ivanov", 1, 10000.0);
        when(employeeService.find(anyString(),anyString())).thenReturn(e);

        System.out.println(employeeService.find("Max", "Ivanov"));
    }

    @Test
    void sum(){
        double actual = departmentService.getEmployeeSalarySum(1);
        assertEquals(400, actual, 0,000001);
    }

    @Test
    void min(){
        double actual = departmentService.getEmployeeMinSalary(1);
        assertEquals(400, actual, 0,000001);
    }

    @Test
    void max(){
        double actual = departmentService.getEmployeeMaxSalary(1);
        assertEquals(400, actual, 0,000001);
    }

    @Test
    void getAllByDepartment(){
        List<Employee> actual = departmentService.getAll(2);
        List<Employee> expected = Arrays.asList(
                new Employee("Ivano", "Ivanov", 2, 10000.0),
                new Employee("Roman", "Ivanov", 2, 10000.0)
        );
        assertEquals(expected.size(),actual.size());
        assertTrue(expected.containsAll(actual));
    }
    @Test
    void getAll(){
        List<Integer> expectedDepartments = employees.stream()
                .map(Employee::getDepartment)
                .distinct()
                .collect(Collectors.toList());
        Map<Integer, List<Employee>> actual = departmentService.getAll();
        assertEquals(expectedDepartments.size(), actual.keySet().size());
        assertTrue(expectedDepartments.containsAll(actual.keySet()));
    }
}
