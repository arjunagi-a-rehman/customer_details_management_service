package com.arjunagi.customer_details_management_service;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "AuditAwareImp")
@OpenAPIDefinition(
		info = @Info(
				title = "Customer Details Management service REST API Documentation",
				description = "This service provides an API's for CURD operations on Customer details",
				version = "v1",
				contact = @Contact(
						name = "Arjunagi A Rehman",
						email = "abdul123arj@gmail.com"
				),
				license = @License(
						name = "Apache 2.0"
				)
		)
)
public class CustomerDetailsManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerDetailsManagementServiceApplication.class, args);
	}

}
