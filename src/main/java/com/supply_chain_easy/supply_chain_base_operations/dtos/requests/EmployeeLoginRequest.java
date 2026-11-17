package com.supply_chain_easy.supply_chain_base_operations.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeLoginRequest {
    private String email;
    private String password;
}
