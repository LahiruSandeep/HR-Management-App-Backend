package edu.icet.service;

import edu.icet.dto.Employee;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {
    void saveEmployee(Employee employee);
}
