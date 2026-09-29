package com.supply_chain_easy.supply_chain_base_operations.utilites;

import java.util.UUID;

public class SystemUtility {

    public static String generateId(String entityName) {

        return entityName.toUpperCase() + "-" +
                UUID.randomUUID().toString()
                        .substring(0, 8)
                        .toUpperCase();
    }
}
