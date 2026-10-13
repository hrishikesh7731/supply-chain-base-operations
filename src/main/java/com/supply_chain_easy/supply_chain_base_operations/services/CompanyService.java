package com.supply_chain_easy.supply_chain_base_operations.services;

import com.supply_chain_easy.supply_chain_base_operations.models.Company;
import com.supply_chain_easy.supply_chain_base_operations.models.Employee;
import com.supply_chain_easy.supply_chain_base_operations.models.Role;
import com.supply_chain_easy.supply_chain_base_operations.transformers.SystemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private final RoleService roleService;
    private final EmployeeService employeeService;

    @Autowired
    public CompanyService(RoleService roleService,EmployeeService employeeService){
        this.roleService=roleService;
        this.employeeService=employeeService;
    }

    public Employee createAdminUserForCompany(Company company, String applicationCode){
        Role adminRole= roleService.createAdminRole(company.getLegalName(),applicationCode);
        Employee adminUser= SystemTransformer.mapCompanyToAdminEmployee(company,adminRole);
        return employeeService.save(adminUser);
    }
}
