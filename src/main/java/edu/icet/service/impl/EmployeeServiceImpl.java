package edu.icet.service.impl;

import edu.icet.dto.Employee;
import edu.icet.entity.EmployeeEntity;
import edu.icet.repository.EmployeeRepository;
import edu.icet.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

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
}
