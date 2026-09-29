package com.supply_chain_easy.supply_chain_base_operations.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;


@SuperBuilder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "procurement-companies")
public class ProcurementCompany extends Company {
    private String procurementCompanyCode;
    private String procurementModel;
    private String procurementStrategy;
    private BigDecimal annualProcurementBudget;
    private BigDecimal annualProcurementSpend;
    private String defaultCurrency;
    private Boolean budgetControlEnabled;
    private Boolean purchaseRequisitionRequired;
    private Boolean purchaseOrderRequired;
    private Boolean sourcingRequired;
    private Boolean contractRequired;
    private Boolean supplierApprovalRequired;
    private Boolean multiLevelApprovalEnabled;
    private Boolean threeWayMatchingEnabled;
}
