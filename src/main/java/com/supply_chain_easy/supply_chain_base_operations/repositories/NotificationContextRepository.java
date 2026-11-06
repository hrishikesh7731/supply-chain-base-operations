package com.supply_chain_easy.supply_chain_base_operations.repositories;
import com.supply_chain_easy.supply_chain_base_operations.models.NotificationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface NotificationContextRepository extends JpaRepository<NotificationContext, UUID>{
}
