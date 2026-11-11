
package com.supply_chain_easy.supply_chain_base_operations.services;

import com.supply_chain_easy.supply_chain_base_operations.enums.NotificationStatus;
import com.supply_chain_easy.supply_chain_base_operations.models.Notification;
import com.supply_chain_easy.supply_chain_base_operations.repositories.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class NotificationService {

    private final EmailHandlerImpl emailHandlerimpl;
    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(
            EmailHandlerImpl emailHandlerimpl,
            NotificationRepository notificationRepository) {
        this.emailHandlerimpl = emailHandlerimpl;
        this.notificationRepository = notificationRepository;
    }

    @Transactional
    public Notification sendNotification(Notification notification) {

        log.info("Processing notification. ID: {}", notification.getSysId());

        // Fetch a managed entity inside the active transaction
        notification = notificationRepository.findById(notification.getSysId())
                .orElseThrow(() ->
                        new RuntimeException("Notification not found"));

        if (notification.isEmail()) {
            log.info("Email notification selected. ID: {}", notification.getSysId());

            emailHandlerimpl.send(notification);

            if (notification.isEmailDelivered()) {
                log.info("Email notification delivered. ID: {}", notification.getSysId());
            } else {
                log.warn("Email notification was not delivered. ID: {}", notification.getSysId());
            }
        }

        if (notification.isSMS()) {
            log.debug("SMS notification selected but delivery is not implemented yet. ID: {}",
                    notification.getSysId());
            notification.setSMSDelivered(true);
        }

        if (notification.isWhatsApp()) {
            log.debug("WhatsApp notification selected but delivery is not implemented yet. ID: {}",
                    notification.getSysId());
            notification.setWhatsAppDelivered(true);
        }

        if ((notification.isEmail() || notification.isSMS() || notification.isWhatsApp())
                && (!notification.isEmail() || notification.isEmailDelivered())
                && (!notification.isSMS() || notification.isSMSDelivered())
                && (!notification.isWhatsApp() || notification.isWhatsAppDelivered())) {

            notification.setNotificationStatus(NotificationStatus.DELIVERED);
            log.info("Notification has been delivered. ID: {}", notification.getSysId());
        }

        notificationRepository.save(notification);

        return notification;
    }

    public List<Notification> fetchByNotificationStatus(
            NotificationStatus notificationStatus) {
        return notificationRepository.findByNotificationStatus(notificationStatus);
    }

    public void saveNotification(Notification notification) {
        notificationRepository.save(notification);
    }
}
