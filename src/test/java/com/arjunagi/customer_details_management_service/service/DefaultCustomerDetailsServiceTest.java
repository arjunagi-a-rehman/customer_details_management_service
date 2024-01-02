package com.arjunagi.customer_details_management_service.service;

import com.arjunagi.customer_details_management_service.dtos.CustomerRequestDto;
import com.arjunagi.customer_details_management_service.dtos.CustomerResponseDto;
import com.arjunagi.customer_details_management_service.exception.CustomerAgeException;
import com.arjunagi.customer_details_management_service.exception.CustomerDetailsAlreadyExistsException;
import com.arjunagi.customer_details_management_service.exception.ResourceNotFoundException;
import com.arjunagi.customer_details_management_service.models.CustomerDetails;
import com.arjunagi.customer_details_management_service.models.Occupation;
import com.arjunagi.customer_details_management_service.repository.ICustomerDetailsRepo;
import com.arjunagi.customer_details_management_service.services.imp.DefaultCustomerDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class DefaultCustomerDetailsServiceTest {

    @Mock
    private ICustomerDetailsRepo customerDetailsRepo;

    private DefaultCustomerDetailsService customerDetailsService;

    @BeforeEach
    void setup(){
        customerDetailsService=new DefaultCustomerDetailsService(customerDetailsRepo);
    }

    @Test
    void testCreateCustomerDetailsRecord() {
        CustomerRequestDto customerRequestDto = new CustomerRequestDto();
        customerRequestDto.setEmail("test@gmail.com");
        customerRequestDto.setDob(LocalDate.now().minusYears(20));
        customerRequestDto.setOccupation(Occupation.Developer);

        when(customerDetailsRepo.findByEmail(any())).thenReturn(Optional.empty());
        when(customerDetailsRepo.findByOccupationAndDobAndCustomerGroup(any(), any(), any())).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> customerDetailsService.createCustomerDetailsRecord(customerRequestDto));

        verify(customerDetailsRepo, times(1)).save(any());
    }

    @Test
    void testCreateCustomerDetailsRecord_AgeBelow18() {
        CustomerRequestDto customerRequestDto = new CustomerRequestDto();
        customerRequestDto.setDob(LocalDate.now().minusYears(15)); // Age below 18

        assertThrows(CustomerAgeException.class, () -> customerDetailsService.createCustomerDetailsRecord(customerRequestDto));
        verify(customerDetailsRepo, never()).save(any());
    }

    @Test
    void testCreateCustomerDetailsRecord_EmailAlreadyExists() {
        CustomerRequestDto customerRequestDto = new CustomerRequestDto();
        customerRequestDto.setEmail("test@gmail.com");

        when(customerDetailsRepo.findByEmail(any())).thenReturn(Optional.of(new CustomerDetails()));

        assertThrows(CustomerDetailsAlreadyExistsException.class, () -> customerDetailsService.createCustomerDetailsRecord(customerRequestDto));
    }

    @Test
    void testCreateCustomerDetailsRecord_OccupationDobGroupAlreadyExists() {
        CustomerRequestDto customerRequestDto = new CustomerRequestDto();
        customerRequestDto.setOccupation(Occupation.Developer);
        customerRequestDto.setDob(LocalDate.of(1990, 1, 1));

        when(customerDetailsRepo.findByEmail(any())).thenReturn(Optional.empty());
        when(customerDetailsRepo.findByOccupationAndDobAndCustomerGroup(any(), any(), any())).thenReturn(Optional.of(new CustomerDetails()));

        assertThrows(CustomerDetailsAlreadyExistsException.class, () -> customerDetailsService.createCustomerDetailsRecord(customerRequestDto));
        verify(customerDetailsRepo, never()).save(any());
    }

    @Test
    void testGetCustomerDetailsByEmail() {
        String email = "test@yahoo.com";
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setEmail(email);

        when(customerDetailsRepo.findByEmail(email)).thenReturn(Optional.of(customerDetails));

        CustomerResponseDto result = customerDetailsService.getCustomerDetailsByEmail(email);

        assertNotNull(result);
        assertEquals(email, result.getEmail());
    }

    @Test
    void testGetCustomerDetailsByEmail_ResourceNotFound() {
        String email = "nonexistent@gmail.com";

        when(customerDetailsRepo.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> customerDetailsService.getCustomerDetailsByEmail(email));
        verify(customerDetailsRepo, never()).save(any()); // Verify that the repository save method is never called
    }
    @Test
    void testUpdateCustomerDetailsRecord() {
        String email = "test@gmail.com";
        CustomerRequestDto customerRequestDto = new CustomerRequestDto();
        customerRequestDto.setEmail(email);
        customerRequestDto.setDob(LocalDate.now().minusYears(25));
        customerRequestDto.setOccupation(Occupation.Developer);

        CustomerDetails existingCustomerDetails = new CustomerDetails();
        existingCustomerDetails.setEmail(email);
        existingCustomerDetails.setDob(LocalDate.now().minusYears(30));
        existingCustomerDetails.setOccupation(Occupation.Chef);

        when(customerDetailsRepo.findByEmail(email)).thenReturn(Optional.of(existingCustomerDetails));
        when(customerDetailsRepo.findByOccupationAndDobAndCustomerGroup(any(), any(), any())).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> customerDetailsService.updateCustomerDetailsRecord(customerRequestDto));

        verify(customerDetailsRepo, times(1)).save(any());
    }

    @Test
    void testUpdateCustomerDetailsRecord_EmailNotFound() {
        String email = "test@gmail.com";
        CustomerRequestDto customerRequestDto = new CustomerRequestDto();
        customerRequestDto.setEmail(email);

        when(customerDetailsRepo.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> customerDetailsService.updateCustomerDetailsRecord(customerRequestDto));
    }


    @Test
    void testDeleteCustomerDetailsByEmail() {
        String email = "test@gmail.com";

        CustomerDetails existingCustomerDetails = new CustomerDetails();
        existingCustomerDetails.setEmail(email);
        customerDetailsRepo.save(existingCustomerDetails);
        when(customerDetailsRepo.findByEmail(email)).thenReturn(Optional.of(existingCustomerDetails));
        boolean result = customerDetailsService.deleteCustomerDetailsByEmail(email);
        verify(customerDetailsRepo, times(1)).delete(any());
    }

    @Test
    void testDeleteCustomerDetailsByEmail_EmailNotFound() {
        String email = "test@gmail.com";
        when(customerDetailsRepo.findByEmail(email)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> customerDetailsService.deleteCustomerDetailsByEmail(email));
        verify(customerDetailsRepo, never()).delete(any());
    }



}
