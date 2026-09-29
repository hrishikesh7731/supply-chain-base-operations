package com.supply_chain_easy.supply_chain_base_operations.models;

import com.supply_chain_easy.supply_chain_base_operations.enums.EmploymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "employees")
public class Employee extends User {

    private String employeeId;
    private String workEmail;
    private String personalEmail;
    private String phoneNumber;

    // =========================
    // Employment Information
    // =========================

    private String jobTitle;
    private String designation;
    private String employeeType;
    private EmploymentStatus employmentStatus;
    private LocalDate joiningDate;
    private LocalDate exitDate;

    @ManyToOne
    private Employee manager;
    private String officeLocation;
    private String country;
    private String state;
    private String city;

    // =========================
    // Procurement Authority
    // =========================

    private BigDecimal approvalLimit;
    private String approvalCurrency;
    private Boolean active;
}