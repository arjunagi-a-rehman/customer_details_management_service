package com.arjunagi.customer_details_management_service.repository;

import com.arjunagi.customer_details_management_service.models.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerDetailsRepo extends JpaRepository<CustomerDetails,Long> {
}
