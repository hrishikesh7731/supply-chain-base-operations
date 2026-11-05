package com.supply_chain_easy.supply_chain_base_operations.schedulejobs;

import com.supply_chain_easy.supply_chain_base_operations.enums.NotificationStatus;
import com.supply_chain_easy.supply_chain_base_operations.models.Notification;
import com.supply_chain_easy.supply_chain_base_operations.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import java.util.List;

public class SystemScheduler {

    private final NotificationService notificationService;

    @Autowired
    public SystemScheduler(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

     @Scheduled(fixedDelay = 5 * 60 * 1000)
     private void processFailedAndInQueueNotification(){

         List<Notification> FailedNotifications=notificationService.fetchByNotificationStatus(NotificationStatus.FAILED);
         List<Notification> combinedNotifications=notificationService.fetchByNotificationStatus(NotificationStatus.IN_QUEUE);
         combinedNotifications.addAll(FailedNotifications);

         for(Notification notification:combinedNotifications){
             notificationService.sendNotification(notification);
         }
     }
}
