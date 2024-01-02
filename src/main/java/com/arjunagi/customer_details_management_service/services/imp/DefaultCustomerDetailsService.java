package com.arjunagi.customer_details_management_service.services.imp;

import com.arjunagi.customer_details_management_service.dtos.CustomerRequestDto;
import com.arjunagi.customer_details_management_service.dtos.CustomerResponseDto;
import com.arjunagi.customer_details_management_service.repository.ICustomerDetailsRepo;
import com.arjunagi.customer_details_management_service.services.ICustomerDetailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DefaultCustomerDetailsService implements ICustomerDetailService {
    private ICustomerDetailsRepo customerDetailsRepo;

    /**
     * @param customerRequestDto
     */
    @Override
    public void createCustomerDetailsRecord(CustomerRequestDto customerRequestDto) {

    }

    /**
     * @param email
     * @return
     */
    @Override
    public CustomerResponseDto getCustomerDetailsByEmail(String email) {
        return null;
    }

    /**
     * @param customerRequestDto
     * @return
     */
    @Override
    public Boolean updateCustomerDetailsRecord(CustomerRequestDto customerRequestDto) {
        return null;
    }

    /**
     * @param email
     * @return
     */
    @Override
    public Boolean deleteCustomerDetailsByEmail(String email) {
        return null;
    }
}
