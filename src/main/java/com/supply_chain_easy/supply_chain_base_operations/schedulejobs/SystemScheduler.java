package com.supply_chain_easy.supply_chain_base_operations.schedulejobs;

import com.supply_chain_easy.supply_chain_base_operations.enums.NotificationStatus;
import com.supply_chain_easy.supply_chain_base_operations.models.Notification;
import com.supply_chain_easy.supply_chain_base_operations.services.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;

@Slf4j
@Component
public class SystemScheduler {

    private final NotificationService notificationService;

    @Autowired
    public SystemScheduler(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Scheduled(fixedDelay = 5 * 60 * 1000)
    public void processFailedAndInQueueNotification() {

        // Process queued notifications without consuming retries
        List<Notification> queuedNotifications =
                notificationService.fetchByNotificationStatus(NotificationStatus.IN_QUEUE);

        for (Notification notification : queuedNotifications) {
            notificationService.sendNotification(notification);
        }

        // Process failed notifications with a retry limit
        List<Notification> failedNotifications =
                notificationService.fetchByNotificationStatus(NotificationStatus.FAILED);

        for (Notification notification : failedNotifications) {

            if (notification.getRetryCount() < 3) {

                notification.setRetryCount(notification.getRetryCount() + 1);
                notification.setNotificationStatus(NotificationStatus.IN_QUEUE);

                notificationService.saveNotification(notification);
                notificationService.sendNotification(notification);

            } else {
                log.warn("Maximum retry attempts reached. Notification ID: {}",
                        notification.getSysId());
            }
        }
    }
}
