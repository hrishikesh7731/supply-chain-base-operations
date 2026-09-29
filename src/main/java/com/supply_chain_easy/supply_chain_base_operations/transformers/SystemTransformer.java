package com.supply_chain_easy.supply_chain_base_operations.transformers;

import com.supply_chain_easy.supply_chain_base_operations.constants.SystemConstant;
import com.supply_chain_easy.supply_chain_base_operations.enums.AuthenticationProvider;
import com.supply_chain_easy.supply_chain_base_operations.enums.EmploymentStatus;
import com.supply_chain_easy.supply_chain_base_operations.enums.UserStatus;
import com.supply_chain_easy.supply_chain_base_operations.models.Company;
import com.supply_chain_easy.supply_chain_base_operations.models.Employee;
import com.supply_chain_easy.supply_chain_base_operations.models.Role;
import com.supply_chain_easy.supply_chain_base_operations.utilites.SystemUtility;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public class SystemTransformer {

    public static Employee mapCompanyToAdminEmployee(Company company, Role adminRole) {

        return Employee.builder()
                // =========================
                // User Information
                // =========================
                .userId(SystemUtility.generateId("USER"))
                .firstName("System")
                .lastName("Administrator")
                .email(company.getEmail())
                .workEmail(company.getEmail())
                .personalEmail(company.getEmail())
                .phoneNumber(company.getPhoneNumber())
                .password(SystemUtility.generateRandomPassword(SystemConstant.DEFAULT_PASSWORD_LENGTH)) // Set encoded password later
                .status(UserStatus.ACTIVE)
                .authenticationProvider(AuthenticationProvider.LOCAL)
                .emailVerified(false)
                .phoneVerified(false)
                .twoFactorEnabled(false)
                .failedLoginAttempts(0)
                .accountLocked(false)
                .profileImageUrl(SystemConstant.DEFAULT_PROFILE_PIC_URL)
                .preferredLanguage("en")
                .preferredCurrency("INR")
                .currency("INR")
                .timeZone("Asia/Kolkata")
                .address(company.getRegisteredAddress())
                .country("India")
                .state(null)
                .city(null)
                .postalCode(null)
                .active(true)
                .lastActiveAt(Instant.now())
                // =========================
                // Employee Information
                // =========================
                .employeeId(SystemUtility.generateId("EMPLOYEE"))
                .jobTitle("Company Administrator")
                .designation("Administrator")
                .employeeType("FULL_TIME")
                .employmentStatus(EmploymentStatus.ACTIVE)
                .joiningDate(LocalDate.now())
                .approvalLimit(BigDecimal.valueOf(999999999))
                .approvalCurrency("INR")
                .officeLocation(company.getRegisteredAddress())
                .manager(null)
                .roles(List.of(adminRole))
                .company(company)
                .build();
    }
}
