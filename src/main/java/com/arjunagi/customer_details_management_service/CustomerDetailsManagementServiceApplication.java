package com.arjunagi.customer_details_management_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "AuditAwareImp")
public class CustomerDetailsManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerDetailsManagementServiceApplication.class, args);
	}

}
