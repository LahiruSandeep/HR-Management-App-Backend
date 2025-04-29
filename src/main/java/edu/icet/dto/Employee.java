package edu.icet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DateFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private String employeeId;
    private String name;
    private String email;
    private String department;
    private String createdAt;
    private String updatedAt;
}
