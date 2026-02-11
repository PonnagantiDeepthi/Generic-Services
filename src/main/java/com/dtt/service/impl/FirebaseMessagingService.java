package com.dtt.service.impl;

import org.springframework.stereotype.Service;

import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.dtt.requestDTO.PushNotificationRequest;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidConfig.Priority;
import com.google.firebase.messaging.ApnsConfig;
import com.google.firebase.messaging.Aps;
import com.google.firebase.messaging.ApsAlert;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;

@Service
public class FirebaseMessagingService {
	
	private final FirebaseMessaging firebaseMessaging;
	
//	@Value("${url.logo.image}")
//	String urlLogoImage;
	
	@Autowired
    AndroidPushNotificationsService androidPushNotificationsService;

    public FirebaseMessagingService(FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
    }

    public String sendNotification(PushNotificationRequest notificationRequest) throws FirebaseMessagingException {
    	
    	Map<String, String> data = new HashMap<String, String>();
    	Map<String,Object> map=androidPushNotificationsService.createMap(notificationRequest.getData().getNotificationContext());
    	
    	data.put("title", notificationRequest.getData().getTitle());
    	data.put("body", notificationRequest.getData().getBody());
    	
    	data.put("notificationContext", map.toString());
    	//data.put("urlImage", urlLogoImage);
    	System.out.println("data ==> "+data);
//    	String token =""; 
//        AndroidNotification androidNotification = AndroidNotification.builder()
//        		.setClickAction("")
//        		.setTitle("test")
//        		.setBody("test for android")
//        		.setIcon("stock_ticker_update")
//                .setColor("#f45342")
//        		.build();
//
        AndroidConfig androidConfig = AndroidConfig.builder()
        		.setPriority(Priority.HIGH)
        		.build();
        
        ApsAlert apsAlert = ApsAlert.builder()
        		.setTitle(notificationRequest.getData().getTitle())
        		.setBody(notificationRequest.getData().getBody())
        		.build();
        
        Aps aps = Aps.builder()
        		.setSound("default")
        		.setAlert(apsAlert)
        		.setMutableContent(true)
        		.build();
    	
        ApnsConfig apnsConfig = ApnsConfig.builder()
        		.setAps(aps)
        		.build();
//    	Notification notification = Notification.builder()
//    			.setTitle(notificationRequest.getData().getTitle())
//    			.setBody(notificationRequest.getData().getBody())
//    			.build();
        Message message = Message
                .builder()
                .setToken(notificationRequest.getTo())
//                .setNotification(notification)
                .setAndroidConfig(androidConfig)
                .setApnsConfig(apnsConfig)
                .putAllData(data)
                .build();
        try {
            // Send the notification and capture the message ID
            String response = firebaseMessaging.send(message);
            System.out.println("Notification sent successfully. Message ID: " + response);
            return "Notification sent successfully. Message ID: " + response;
        } catch (FirebaseMessagingException e) {
            // Handle exceptions and log errors
            System.err.println("Failed to send notification: " + e.getMessage());
            e.printStackTrace();
            return "Failed to send notification: " + e.getMessage();
        }
    }
    
//    public String sendNotification(PushNotificationRequest notificationRequest) throws FirebaseMessagingException {
//
//        Map<String, String> data = new HashMap<>();
//
//        Map<String, Object> map = androidPushNotificationsService
//                .createMap(notificationRequest.getData().getNotificationContext());
//
//        data.put("title", notificationRequest.getData().getTitle());
//        data.put("body", notificationRequest.getData().getBody());
//
//        data.put("notificationContext", map.toString()); // ⚠️ replace with JSON string if possible
//
//        AndroidConfig androidConfig = AndroidConfig.builder()
//                .setPriority(Priority.HIGH)
//                .build();
//
//        ApnsConfig apnsConfig = ApnsConfig.builder()
//                .setAps(Aps.builder()
//                        .setSound("default")
//                        .setAlert(ApsAlert.builder()
//                                .setTitle(notificationRequest.getData().getTitle())
//                                .setBody(notificationRequest.getData().getBody())
//                                .build())
//                        .build())
//                .build();
//
//        Message message = Message.builder()
//                .setToken(notificationRequest.getTo())
//                .setAndroidConfig(androidConfig)
//                .setApnsConfig(apnsConfig)
//                .putAllData(data)
//                .build();
//
//        String messageId = firebaseMessaging.send(message); // ✅ if fail it throws exception
//        return "Notification sent successfully. Message ID: " + messageId;
//    }


}

