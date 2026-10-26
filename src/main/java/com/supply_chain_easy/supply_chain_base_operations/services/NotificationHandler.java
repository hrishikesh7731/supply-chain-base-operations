package com.supply_chain_easy.supply_chain_base_operations.services;

import com.supply_chain_easy.supply_chain_base_operations.models.Notification;

public interface NotificationHandler {
    public Notification send(Notification notification);
}
