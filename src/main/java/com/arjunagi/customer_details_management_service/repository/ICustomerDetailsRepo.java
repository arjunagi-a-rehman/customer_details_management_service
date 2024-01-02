package com.arjunagi.customer_details_management_service.repository;

import com.arjunagi.customer_details_management_service.models.CustomerDetails;
import com.arjunagi.customer_details_management_service.models.CustomerGroup;
import com.arjunagi.customer_details_management_service.models.Occupation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ICustomerDetailsRepo extends JpaRepository<CustomerDetails,Long> {
    Optional<CustomerDetails> findByEmail(String email);
    Optional<CustomerDetails> findByOccupationAndDobAndCustomerGroup(Occupation occupation, LocalDate dob, CustomerGroup customerGroup);
}
