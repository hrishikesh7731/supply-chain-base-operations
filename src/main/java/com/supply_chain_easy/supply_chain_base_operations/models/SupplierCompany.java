package com.supply_chain_easy.supply_chain_base_operations.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "supplier-companies")
@SuperBuilder
@Entity
public class SupplierCompany extends Company{
    private String supplierCode;
    private String supplierCategory; // MANUFACTURER, SERVICE
    private Integer leadTimeDays;
    private BigDecimal minimumOrderValue;
}