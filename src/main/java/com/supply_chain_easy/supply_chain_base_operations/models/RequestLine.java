package com.supply_chain_easy.supply_chain_base_operations.models;

import com.supply_chain_easy.supply_chain_base_operations.enums.RequestLineStatus;
import com.supply_chain_easy.supply_chain_base_operations.enums.UnitOfMeasure;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "purchase_request_lines")
@Entity
public class RequestLine extends GlobalRecord{
    @Column(nullable = false)
    private Integer lineNumber;

    @ManyToOne
    private Product product;

    @Column(name = "item_description", nullable = false, length = 2000)
    private String itemDescription;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "unit_of_measure", nullable = false, length = 30)
    private UnitOfMeasure unitOfMeasure;

    @Enumerated(EnumType.STRING)
    @Column(name = "line_status", nullable = false, length = 30)
    private RequestLineStatus lineStatus = RequestLineStatus.OPEN;

    @Column(length = 1000)
    private String notes;
}
