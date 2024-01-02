package com.arjunagi.customer_details_management_service.services;

import com.arjunagi.customer_details_management_service.dtos.ApiResponseDto;
import com.arjunagi.customer_details_management_service.dtos.CustomerRequestDto;
import com.arjunagi.customer_details_management_service.dtos.CustomerResponseDto;
import com.arjunagi.customer_details_management_service.models.CustomerDetails;

public interface ICustomerDetailService {
    public void createCustomerDetailsRecord(CustomerRequestDto customerRequestDto);
    public CustomerResponseDto getCustomerDetailsByEmail(String email);
    public Boolean updateCustomerDetailsRecord(CustomerRequestDto customerRequestDto);
    public Boolean deleteCustomerDetailsByEmail(String email);
}
