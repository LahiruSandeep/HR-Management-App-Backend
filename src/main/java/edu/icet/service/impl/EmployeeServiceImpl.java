package edu.icet.service.impl;

import edu.icet.dto.Employee;
import edu.icet.dto.Otp;
import edu.icet.dto.SignInRequest;
import edu.icet.entity.EmployeeEntity;
import edu.icet.repository.EmployeeRepository;
import edu.icet.service.EmployeeService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final ModelMapper modelMapper;
    private final EmployeeRepository employeeRepository;
    private final JdbcTemplate jdbcTemplate;

    private final JavaMailSender mailSender;
    public static final String SENDER_EMAIL = "taskinahned774@gmail.com";
    private final PasswordEncoder passwordEncoder;
    private final String adminId="12345";

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
            List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
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
            List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
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

    @Override
    public void authenticate(String email) {
        try {
            EmployeeEntity employeeEntity = employeeRepository.findByEmail(email).orElseThrow(() -> new Exception("Could not find"));
            if(employeeEntity != null){
                String otp=createOTP();
                employeeEntity.setOtpNumber(otp);
                employeeRepository.save(employeeEntity);
                String subject = "Authentication";
                String body = "Dear " + employeeEntity.getFullName() + ",\n\n" +
                        "Your OTP is : \n\n" + otp +" "+
                        "Best regards,\n" +
                        "Taskin Ahned";
                sendEmail(employeeEntity.getEmail(), subject, body);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Employee authenticateEmployee(SignInRequest signInRequest) throws Exception {
        EmployeeEntity byEmail = employeeRepository.findByEmailAndIsDisabledFalse(signInRequest.getEmail());
        if(byEmail.getPassword().equals(signInRequest.getPassword())){
            return modelMapper.map(byEmail, Employee.class);
        }else{
            throw new Exception("Invalid password");
        }
    }

    @Override
    public boolean validateOtp(Otp otp) {
        try {
            EmployeeEntity employeeEntity = employeeRepository.findByEmail(otp.getEmail()).orElseThrow(() -> new Exception("Could not find"));
            if(employeeEntity.getOtpNumber().equals(otp.getOtpNumber())){
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public EmployeeEntity resetPassword(SignInRequest request) {
        try{
            EmployeeEntity employeeEntity = employeeRepository.findByEmail(request.getEmail()).orElseThrow(() -> new Exception("Could not find"));
            employeeEntity.setPassword(request.getPassword());
            EmployeeEntity saved = employeeRepository.save(employeeEntity);
            return modelMapper.map(saved, EmployeeEntity.class);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private String createOTP() {
        return String.format("%04d", new Random().nextInt(10000));
    }

    boolean sendEmail(String toEmail, String subject, String body) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(SENDER_EMAIL);
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(body, true);

            mailSender.send(message);

            return true;
        } catch (MessagingException e) {

            throw new RuntimeException("Failed to send email", e);
        }
    }


}
