package com.example.messaging_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class MessagingApiApplication {

	public static void main(String[] args) {
		// Run the Spring Boot application and get the application context
		var context = SpringApplication.run(MessagingApiApplication.class, args);

		// Retrieve the server port
		String port = context.getEnvironment().getProperty("server.port");

		// Retrieve the server address
		String address = context.getEnvironment().getProperty("server.address");

		// Print the server address and port
		System.out.println("Server is running at: http://" + (address != null ? address : "localhost") + ":" + (port != null ? port : "8080"));

		// Close the application context
		// context.close();
	}
}