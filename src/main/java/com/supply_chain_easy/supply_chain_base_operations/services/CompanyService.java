package com.supply_chain_easy.supply_chain_base_operations.services;

import com.supply_chain_easy.supply_chain_base_operations.models.Company;
import com.supply_chain_easy.supply_chain_base_operations.models.Role;
import com.supply_chain_easy.supply_chain_base_operations.models.User;
import org.springframework.beans.factory.annotation.Autowired;

public class CompanyService {

    private final RoleService roleService;

    @Autowired
    public CompanyService(RoleService roleService){
        this.roleService=roleService;
    }
    public User createAdminUserForCompany(Company company){
        Role adminRole= roleService.createAdminRole(company.getLegalName());
    }
}
