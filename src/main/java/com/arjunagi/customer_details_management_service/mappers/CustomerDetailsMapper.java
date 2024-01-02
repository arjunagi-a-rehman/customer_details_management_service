package com.arjunagi.customer_details_management_service.mappers;

import com.arjunagi.customer_details_management_service.dtos.CustomerRequestDto;
import com.arjunagi.customer_details_management_service.dtos.CustomerResponseDto;
import com.arjunagi.customer_details_management_service.models.CustomerDetails;

public class CustomerDetailsMapper {
    public static CustomerDetails CustomerRequestDtoToCustomerDetails(CustomerRequestDto customerRequestDto,CustomerDetails customerDetails){
        customerDetails.setName(customerRequestDto.getName());
        customerDetails.setDob(customerRequestDto.getDob());
        customerDetails.setEmail(customerRequestDto.getEmail());
        customerDetails.setOccupation(customerRequestDto.getOccupation());
        return customerDetails;
    }
    public static CustomerResponseDto CustomerDetailsToCustomerResponseDto(CustomerDetails customerDetails,CustomerResponseDto customerResponseDto){
        customerResponseDto.setCustomerGroup(customerDetails.getCustomerGroup());
        customerResponseDto.setName(customerDetails.getName());
        customerResponseDto.setDob(customerDetails.getDob());
        customerResponseDto.setEmail(customerDetails.getEmail());
        customerResponseDto.setOccupation(customerDetails.getOccupation());
        return customerResponseDto;
    }
}
