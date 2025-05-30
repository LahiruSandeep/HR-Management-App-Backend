package edu.icet.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String employeeId;
    private String name;
    private String email;
    private String department;
    private String createdAt;
    private String updatedAt;
    private boolean isDisabled;


    public void setOtpNumber(String otp) {
    }

    public String getFullName() {
        return null;
    }

    public Object getPassword() {
        return null;
    }

    public Object getOtpNumber() {
        return null;
    }

    public void setPassword(String password) {
    }
}
