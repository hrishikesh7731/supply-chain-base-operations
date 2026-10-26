package com.supply_chain_easy.supply_chain_base_operations.services;

import com.supply_chain_easy.supply_chain_base_operations.models.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationService {

   public void sendNotification(Notification notification){
       if(notification.isEmail()){

       }
       if(notification.isSMS()){

       }
       if(notification.isWhatsApp()){

       }
   }

}