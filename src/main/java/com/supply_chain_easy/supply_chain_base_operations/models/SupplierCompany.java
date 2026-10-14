package com.supply_chain_easy.supply_chain_base_operations.models;

import com.supply_chain_easy.supply_chain_base_operations.enums.SupplierCategory;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "supplier_companies")
@SuperBuilder
@Entity
public class SupplierCompany extends Company{
    private String supplierCode;
    @Enumerated(EnumType.STRING)
    private SupplierCategory supplierCategory;
    private Integer leadTimeDays;
    private BigDecimal minimumOrderValue;
}