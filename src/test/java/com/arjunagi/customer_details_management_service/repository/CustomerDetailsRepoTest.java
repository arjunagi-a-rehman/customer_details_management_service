package com.arjunagi.customer_details_management_service.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Optional;

import com.arjunagi.customer_details_management_service.models.CustomerDetails;
import com.arjunagi.customer_details_management_service.models.CustomerGroup;
import com.arjunagi.customer_details_management_service.models.Occupation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CustomerDetailsRepoTest {

    @Mock
    private ICustomerDetailsRepo customerDetailsRepo;

    @Test
    void testFindByEmail() {
        // Arrange
        String email = "zander@gamil.com";
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setEmail(email);
        when(customerDetailsRepo.findByEmail(email)).thenReturn(Optional.of(customerDetails));

        // Act
        Optional<CustomerDetails> result = customerDetailsRepo.findByEmail(email);
        // Assert
        assertEquals(customerDetails, result.orElse(null));
    }

    @Test
    void testFindByOccupationAndDobAndCustomerGroup() {
        // Arrange
        Occupation occupation = Occupation.Developer;
        LocalDate dob = LocalDate.of(1990, 1, 1);
        CustomerGroup customerGroup = CustomerGroup.Developer;
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setOccupation(occupation);
        customerDetails.setDob(dob);
        customerDetails.setCustomerGroup(customerGroup);
        when(customerDetailsRepo.findByOccupationAndDobAndCustomerGroup(occupation, dob, customerGroup))
                .thenReturn(Optional.of(customerDetails));

        // Act
        Optional<CustomerDetails> result = customerDetailsRepo.findByOccupationAndDobAndCustomerGroup(occupation, dob, customerGroup);

        // Assert
        assertEquals(customerDetails, result.orElse(null));
    }
}
