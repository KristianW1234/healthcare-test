package com.testing.healthcare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.testing.healthcare.config.EnvLoader;

@SpringBootApplication
public class HealthcareApplication {

	public static void main(String[] args) {
		EnvLoader.loadEnv();
		SpringApplication.run(HealthcareApplication.class, args);
	}

}
