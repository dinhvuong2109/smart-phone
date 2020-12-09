package com.smartphone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.smartphone")
public class SmartPhoneApplication {
	public static void main(String[] args) {
		SpringApplication.run(SmartPhoneApplication.class, args);
	}

}
