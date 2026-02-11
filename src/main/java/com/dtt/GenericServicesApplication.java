package com.dtt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;

@SpringBootApplication
public class GenericServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GenericServicesApplication.class, args);
	}

	
	@Bean
	FirebaseMessaging firebaseMessaging() throws Exception {
		GoogleCredentials googleCredentials = GoogleCredentials
				.fromStream(new ClassPathResource("sma-firebase-adminsdk.json").getInputStream());
		FirebaseOptions firebaseOptions = FirebaseOptions.builder().setCredentials(googleCredentials).build();
		FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions, "my-app");
		return FirebaseMessaging.getInstance(app);
	}

//	@Bean
//    public FirebaseMessaging firebaseMessaging() throws Exception {
//
//        GoogleCredentials googleCredentials = GoogleCredentials
//                .fromStream(new ClassPathResource("sma-firebase-adminsdk.json").getInputStream());
//
//        FirebaseOptions firebaseOptions = FirebaseOptions.builder()
//                .setCredentials(googleCredentials)
//                .build();
//
//        FirebaseApp app;
//
//        if (FirebaseApp.getApps().isEmpty()) {
//            app = FirebaseApp.initializeApp(firebaseOptions, "my-app");
//        } else {
//            app = FirebaseApp.getInstance("my-app");
//        }
//
//        return FirebaseMessaging.getInstance(app);
//    }s
}
