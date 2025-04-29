package edu.icet.service;

import edu.icet.dto.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    void saveEmployee(Employee employee);

    List<Employee> getAllEmployees();

    List<Employee> updateEmployee(Employee employee);

    void deleteById(String employeeId);
}
