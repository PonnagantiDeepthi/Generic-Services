package com.dtt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.dtt.requestDTO.PushNotificationRequest;
import com.dtt.service.impl.AndroidPushNotificationsService;
import com.dtt.service.impl.FirebaseMessagingService;
import com.google.firebase.messaging.FirebaseMessagingException;

@Controller
public class CustumNotificationService {

	@Autowired
	AndroidPushNotificationsService androidPushNotificationsService;

	@Autowired
	FirebaseMessagingService messagingService;

	@PostMapping("/send/notification")
	public ResponseEntity<String> sentIosNotification(@RequestBody PushNotificationRequest notificationRequest)
			throws FirebaseMessagingException {
		try {
			System.out.println("in controller");
			String response = messagingService.sendNotification(notificationRequest);
			System.out.println("response => " + response);
			return ResponseEntity.ok(response); // ✅ returns success message
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body("Notification failed: " + e.getMessage());
		}

		// return new ResponseEntity<String>(HttpStatus.OK);
	}

//	@PostMapping("/send/notification")
//	public ResponseEntity<String> sendNotification(@RequestBody PushNotificationRequest request) {
//
//		try {
//			String response = messagingService.sendNotification(request);
//			return ResponseEntity.ok(response); // ✅ returns success message
//		} catch (Exception e) {
//			e.printStackTrace();
//			return ResponseEntity.internalServerError().body("Notification failed: " + e.getMessage());
//		}
//	}

}
