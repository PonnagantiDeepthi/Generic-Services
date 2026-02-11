package com.dtt.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.core.io.ClassPathResource;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class FCMInitializer {

	@PostConstruct
	public void initialize() {
		try {

			String path = FCMInitializer.class.getClassLoader().getResource("sma-firebase-adminsdk.json").toString();
			System.out.println("path ==> " + path);
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(new ClassPathResource(path).getInputStream())).build();
			if (FirebaseApp.getApps().isEmpty()) {
				FirebaseApp.initializeApp(options, "sma-staging");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	@Component
//	public class FCMInitializer {
//	    @PostConstruct
//	    public void initialize() {
//	        try {
//	            FirebaseOptions options = FirebaseOptions.builder()
//	                    .setCredentials(GoogleCredentials.fromStream(
//	                            new ClassPathResource("sma-firebase-adminsdk.json").getInputStream()
//	                    ))
//	                    .build();
//	            if (FirebaseApp.getApps().isEmpty()) {
//	                FirebaseApp.initializeApp(options, "my-app");
//	            }
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
//	    }
//	}

}
