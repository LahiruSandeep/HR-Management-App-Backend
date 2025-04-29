package edu.icet.service.impl;

import edu.icet.dto.Employee;
import edu.icet.entity.EmployeeEntity;
import edu.icet.repository.EmployeeRepository;
import edu.icet.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final ModelMapper modelMapper;
    private final EmployeeRepository employeeRepository;
    private final JdbcTemplate jdbcTemplate;
    @Override
    public void saveEmployee(Employee employee) {
        try {
            employeeRepository.save(modelMapper.map(employee, EmployeeEntity.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Employee> getAllEmployees() {
        ArrayList<Employee> employeeArrayList=new ArrayList<>();
        try {
            List<EmployeeEntity> employeeEntities = employeeRepository.findByIsDisabledFalse();
            employeeEntities.forEach(employeeEntity -> {
                employeeArrayList.add(modelMapper.map(employeeEntity, Employee.class));
            });
            return employeeArrayList;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Employee> updateEmployee(Employee employee) {
        ArrayList<Employee> employeeArrayList=new ArrayList<>();
        try{
            employeeRepository.save(modelMapper.map(employee,EmployeeEntity.class));
            List<EmployeeEntity> employeeEntities = employeeRepository.findByIsDisabledFalse();
            employeeEntities.forEach(employeeEntity -> {
                employeeArrayList.add(modelMapper.map(employeeEntity, Employee.class));
            });
            return employeeArrayList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(String employeeId) {
        try {
            EmployeeEntity byEmployeeId = EmployeeRepository.findByEmployeeId(employeeId);
            byEmployeeId.setDisabled(true);
            employeeRepository.save(byEmployeeId);
        } catch (Exception e) {
            System.out.println("Error disabling employee: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }
}
