package com.akanksha.repository;

import com.sneha.entity.Employee;
import java.util.HashMap;
import java.util.Map;

public class EmployeeRepository {
    private Map<Integer, Employee> employees = new HashMap<>();

    public void addEmployee(int employeeId, String employeeName) {
        employees.put(employeeId, new Employee(employeeId, employeeName));
    }

    public Employee getEmployee(int employeeId) {
        return employees.get(employeeId);
    }

    public Map<Integer, Employee> getAllEmployees() {
        return employees;
    }

    public boolean employeeExists(int employeeId) {
        return employees.containsKey(employeeId);
    }
}
