package com.supply_chain_easy.supply_chain_base_operations.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role extends GlobalRecord{
    private String roleId;
    private String roleName;
    @ManyToMany
    private List<Operation> operations;
}
