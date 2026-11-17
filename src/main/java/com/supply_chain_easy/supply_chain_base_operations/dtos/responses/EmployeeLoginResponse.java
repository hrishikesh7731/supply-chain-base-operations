package com.supply_chain_easy.supply_chain_base_operations.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeLoginResponse {
    private String token;
}
