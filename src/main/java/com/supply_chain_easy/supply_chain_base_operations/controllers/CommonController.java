package com.supply_chain_easy.supply_chain_base_operations.controllers;

import com.supply_chain_easy.supply_chain_base_operations.dtos.requests.EmployeeLoginRequest;
import com.supply_chain_easy.supply_chain_base_operations.dtos.responses.EmployeeLoginResponse;
import com.supply_chain_easy.supply_chain_base_operations.dtos.responses.ErrorResponse;
import com.supply_chain_easy.supply_chain_base_operations.exceptions.InvalidCredentialsException;
import com.supply_chain_easy.supply_chain_base_operations.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/common")
public class CommonController {

    private final AuthService authService;

    @Autowired
    public CommonController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/emp/login")
    public ResponseEntity<?> employeeLogin(@RequestBody EmployeeLoginRequest employeeLoginRequest) {

        try{
            EmployeeLoginResponse employeeLoginResponse= authService.authenticateUser(employeeLoginRequest);
            return new ResponseEntity<>(employeeLoginResponse, HttpStatus.OK);
        }
        catch(InvalidCredentialsException e){
            ErrorResponse failureResponse=new ErrorResponse(HttpStatus.UNAUTHORIZED,e.getMessage());
            return new ResponseEntity<>(failureResponse,HttpStatus.UNAUTHORIZED);
        }
        catch(Exception e){
            ErrorResponse failureResponse=new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,"An unexpected error occurred");
            return new ResponseEntity<>(failureResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
