package com.cafe.management;

import com.cafe.models.Employee;
import com.cafe.database.EmployeeDAO;
import java.time.LocalDate;
import java.util.List;

public class EmployeeManager {

    public void addEmployee(String name, String position, double salary, String phoneNumber, LocalDate hireDate, String email) {
        Employee employee = new Employee(0, name, position, salary, phoneNumber, hireDate, email);
        EmployeeDAO.addEmployee(employee);
    }

    public void updateEmployee(int id, String name, String position, double salary, String phoneNumber, LocalDate hireDate, String email) {
        Employee employee = new Employee(id, name, position, salary, phoneNumber, hireDate, email);
        EmployeeDAO.updateEmployee(employee);
    }

    public void deleteEmployee(int id) {
        EmployeeDAO.deleteEmployee(id);
    }

    public Employee getEmployee(int id) {
        return EmployeeDAO.getEmployeeById(id);
    }

    public List<Employee> getAllEmployees() {
        return EmployeeDAO.getAllEmployees();
    }

    public double getTotalPayroll() {
        return getAllEmployees().stream()
                .mapToDouble(Employee::getSalary)
                .sum();
    }
}
