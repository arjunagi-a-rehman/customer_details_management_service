package com.arjunagi.customer_details_management_service.services.imp;

import com.arjunagi.customer_details_management_service.dtos.CustomerRequestDto;
import com.arjunagi.customer_details_management_service.dtos.CustomerResponseDto;
import com.arjunagi.customer_details_management_service.exception.CustomerAgeException;
import com.arjunagi.customer_details_management_service.exception.CustomerDetailsAlreadyExistsException;
import com.arjunagi.customer_details_management_service.mappers.CustomerDetailsMapper;
import com.arjunagi.customer_details_management_service.models.CustomerDetails;
import com.arjunagi.customer_details_management_service.models.CustomerGroup;
import com.arjunagi.customer_details_management_service.models.Occupation;
import com.arjunagi.customer_details_management_service.repository.ICustomerDetailsRepo;
import com.arjunagi.customer_details_management_service.services.ICustomerDetailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@AllArgsConstructor
public class DefaultCustomerDetailsService implements ICustomerDetailService {
    private ICustomerDetailsRepo customerDetailsRepo;

    /**
     * @param customerRequestDto
     */
    @Override
    public void createCustomerDetailsRecord(CustomerRequestDto customerRequestDto) {
        
        if(customerDetailsRepo.findByEmail(customerRequestDto.getEmail()).isPresent())
            throw new CustomerDetailsAlreadyExistsException("The customer with email "+customerRequestDto.getEmail()+" already exist");

        if (ChronoUnit.YEARS.between(customerRequestDto.getDob(), LocalDate.now()) < 18) {
            throw new CustomerAgeException("The customer must be older than 18");
        }

        CustomerGroup customerGroup=getCustomerGroup(customerRequestDto);

        if(customerDetailsRepo.findByOccupationAndDobAndCustomerGroup(customerRequestDto.getOccupation(),customerRequestDto.getDob(),customerGroup).isPresent())
            throw new CustomerDetailsAlreadyExistsException("The customer with occupation "+customerRequestDto.getOccupation()+" ,date of birth "+customerRequestDto.getDob()+" and Customer group "+customerGroup+" already exist");

        CustomerDetails customerDetails= CustomerDetailsMapper.CustomerRequestDtoToCustomerDetails(customerRequestDto,new CustomerDetails());
        customerDetails.setCustomerGroup(customerGroup);

        customerDetailsRepo.save(customerDetails);

    }

    private CustomerGroup getCustomerGroup(CustomerRequestDto customerRequestDto){
        String email=customerRequestDto.getEmail();
        Occupation occupation=customerRequestDto.getOccupation();

        if(email.substring(email.indexOf("@")).equals("@hikeon.tech"))return CustomerGroup.Hikeon;
        if(occupation.equals(Occupation.Developer))return CustomerGroup.Developer;
        if(occupation.equals(Occupation.Chef))return CustomerGroup.Chef;
        return CustomerGroup.NA;
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
