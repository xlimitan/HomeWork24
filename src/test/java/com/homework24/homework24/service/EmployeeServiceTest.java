package com.homework24.homework24.service;

import com.homework24.homework24.exception.EmployeeAlreadyAddedException;
import com.homework24.homework24.exception.EmployeeNotFoundException;
import com.homework24.homework24.exception.EmployeeStoragesFullException;
import com.homework24.homework24.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {

    EmployeeService employeeService = new EmployeeService();

    @Test
    void getAll() {
        Employee e1 = new Employee("Ivano", "Ivanov", 3, 10000.0);
        Employee e2 = new Employee("Roman", "Ivanov", 3, 10000.0);
        employeeService.add(e1);
        employeeService.add(e2);
        System.out.println(employeeService.getAll().size());
        List<Employee> expected = Arrays.asList(e1, e2);
        assertEquals(2, employeeService.getAll().size());
        Assertions.assertIterableEquals(expected, employeeService.getAll());
    }

    @Test
    void add() {
        int prevSize = employeeService.getAll().size();

        Employee e1 = new Employee("Ivano", "Ivanov", 3, 10000.0);
        employeeService.add(e1);
        assertEquals(prevSize +1, employeeService.getAll().size());
        assertTrue(employeeService.getAll().contains(e1));
    }

    @Test
    void whenAddDuplicateThenTrowException() {
        Employee e1 = new Employee("Ivano", "Ivanov", 3, 10000.0);
        employeeService.add(e1);
        assertThrows(EmployeeAlreadyAddedException.class, () -> employeeService.add(e1));
    }

    @Test
    void whenStorageIsFullThenTrowException() {
        for (int i = 0; i < 5; i++) {
            Employee e = new Employee(" " +i, " "+i, 3, 10000.0);
            assertDoesNotThrow(() ->
                    employeeService.add(e));
        }
        assertThrows(EmployeeStoragesFullException.class,
                () -> employeeService.add(new Employee("Ivano", "Ivanov", 3, 10000.0)));
    }

    @Test
    void findPositive(){
        Employee expected = new Employee("Ivano", "Ivanov", 3, 10000.0);
        employeeService.add(expected);
        Employee actual = employeeService.find("Ivano", "Ivanov");
        assertNotNull(actual);
        assertEquals(expected, actual);
    }
    @Test
    void findNegative(){
        Employee expected = new Employee("Ivano", "Ivanov", 3, 10000.0);
        employeeService.add(expected);
        Employee actual = employeeService.find("Marina", "Ivanov");
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.find("Marina", "Ivanov"));
    }

    @Test
    void remove (){
        Employee e = new Employee("Ivano", "Ivanov", 3, 10000.0);
        employeeService.add(e);
        assertTrue(employeeService.getAll().contains(e));
        employeeService.remove("Ivano", "Ivanov");
        assertFalse(employeeService.getAll().contains(e));
    }
}
