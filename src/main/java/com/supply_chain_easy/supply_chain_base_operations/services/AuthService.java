package com.supply_chain_easy.supply_chain_base_operations.services;

import com.supply_chain_easy.supply_chain_base_operations.dtos.requests.EmployeeLoginRequest;
import com.supply_chain_easy.supply_chain_base_operations.dtos.responses.EmployeeLoginResponse;
import com.supply_chain_easy.supply_chain_base_operations.exceptions.InvalidCredentialsException;
import com.supply_chain_easy.supply_chain_base_operations.models.Employee;
import com.supply_chain_easy.supply_chain_base_operations.utilites.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AuthService {

    private final EmployeeService employeeService;
    private final JwtUtility jwtUtility;

    @Autowired
    public AuthService(EmployeeService employeeService, JwtUtility jwtUtility) {
        this.employeeService = employeeService;
        this.jwtUtility = jwtUtility;
    }

    public EmployeeLoginResponse authenticateUser(EmployeeLoginRequest employeeLoginRequest){
        String email=employeeLoginRequest.getEmail();
        String password=employeeLoginRequest.getPassword();
        Employee employee=employeeService.getEmployeeByWorkEmail(email);

        if(employee==null || !employee.getPassword().equals(password)){
            throw new InvalidCredentialsException("Wrong Email or Password Entered");
        }
        employee.setLastLoginAt(Instant.now());
        employeeService.save(employee);
        String token= jwtUtility.generateJwtToken(employee);

        return new  EmployeeLoginResponse(token);
    }

}
