package edu.icet.controller;

import edu.icet.dto.Employee;
import edu.icet.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@CrossOrigin
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/create")
    ResponseEntity<String> saveEmployee(@RequestBody Employee employee){
        try{
            employeeService.saveEmployee(employee);
            return ResponseEntity.ok("Item saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/retrieve/all")
    ResponseEntity<List<Employee>> getAllEmployees(){
        try{
            return ResponseEntity.ok(employeeService.getAllEmployees());
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    ResponseEntity<List<Employee>> updateEmployee(@RequestBody Employee employee){
        try{
            return new ResponseEntity<List<Employee>>(employeeService.updateEmployee(employee),HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
