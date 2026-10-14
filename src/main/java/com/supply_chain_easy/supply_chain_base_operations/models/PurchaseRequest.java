package com.supply_chain_easy.supply_chain_base_operations.models;

import com.supply_chain_easy.supply_chain_base_operations.enums.ProcurementType;
import com.supply_chain_easy.supply_chain_base_operations.enums.PurchaseRequestType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "purchase_requests")
@Entity
public class PurchaseRequest extends Request {

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private PurchaseRequestType purchaseRequestType;

    @ManyToOne
    private Employee submitterEmployee;

    @ManyToOne
    private Employee procurementApprovalManager;

    @ManyToOne
    private Employee supplierReviewerApprovalEmployee;

    @ManyToOne
    private Employee supplierManagerApproval;


    private String deliveryLocation;

    @Column(precision = 19, scale = 4)
    private BigDecimal estimatedTotalAmount;

    @Column(length = 3)
    private String currencyCode;

    private String budgetCode;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private ProcurementType procurementType;
}
