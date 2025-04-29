package edu.icet.repository;

import edu.icet.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Integer> {
    static EmployeeEntity findByEmployeeId(String employeeId) {
        return null;
    }

    List<EmployeeEntity> findByIsDisabledFalse();
}
