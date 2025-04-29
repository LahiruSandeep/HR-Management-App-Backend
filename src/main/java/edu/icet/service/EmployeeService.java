package edu.icet.service;

import edu.icet.dto.Employee;
import edu.icet.dto.Otp;
import edu.icet.dto.SignInRequest;
import edu.icet.entity.EmployeeEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    void saveEmployee(Employee employee);

    List<Employee> getAllEmployees();

    List<Employee> updateEmployee(Employee employee);

    void deleteById(String employeeId);

    void authenticate(String email);

    Employee authenticateEmployee(SignInRequest signInRequest) throws Exception;

    boolean validateOtp(Otp otp);

    EmployeeEntity resetPassword(SignInRequest request);

}
