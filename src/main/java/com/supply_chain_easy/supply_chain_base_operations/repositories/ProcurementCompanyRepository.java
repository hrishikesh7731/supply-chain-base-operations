package com.supply_chain_easy.supply_chain_base_operations.repositories;

import com.supply_chain_easy.supply_chain_base_operations.models.ProcurementCompany;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProcurementCompanyRepository extends JpaRepository<ProcurementCompany, UUID> {
}
