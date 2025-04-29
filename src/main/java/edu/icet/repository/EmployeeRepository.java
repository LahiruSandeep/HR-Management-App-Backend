package edu.icet.repository;

import edu.icet.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Integer> {
    static EmployeeEntity findByEmployeeId(String employeeId) {
        return null;
    }
    Optional<EmployeeEntity> findByEmail(String email);

    EmployeeEntity findByEmailAndIsDisabledFalse(String email);
}
