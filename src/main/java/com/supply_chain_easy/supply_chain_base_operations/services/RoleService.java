package com.supply_chain_easy.supply_chain_base_operations.services;

import com.supply_chain_easy.supply_chain_base_operations.models.Operation;
import com.supply_chain_easy.supply_chain_base_operations.models.Role;
import com.supply_chain_easy.supply_chain_base_operations.repositories.RoleRepository;
import com.supply_chain_easy.supply_chain_base_operations.utilites.SystemUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RoleService {

    private final OperationService operationService;
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(OperationService operationService,RoleRepository roleRepository){
        this.operationService=operationService;
        this.roleRepository=roleRepository;
    }

    public Role createAdminRole(String companyName){

        List<Operation> operations= operationService.fetchAllOperations();

        Role adminrole= Role.builder()
                .roleId(SystemUtility.generateId("ROLE"))
                .roleName(companyName+"_"+"MAINT")
                .operations(operations)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .createdBy("system")
                .updatedBy("system")
                .build();

        return roleRepository.save(adminrole);
    }
}
