package com.arjunagi.customer_details_management_service.services.imp;

import com.arjunagi.customer_details_management_service.dtos.CustomerRequestDto;
import com.arjunagi.customer_details_management_service.dtos.CustomerResponseDto;
import com.arjunagi.customer_details_management_service.exception.CustomerAgeException;
import com.arjunagi.customer_details_management_service.exception.CustomerDetailsAlreadyExistsException;
import com.arjunagi.customer_details_management_service.exception.ResourceNotFoundException;
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
import java.util.Optional;

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

        if (email != null && email.contains("@hikeon.tech")) {
            return CustomerGroup.Hikeon;
        }
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

        CustomerDetails customerDetails=customerDetailsRepo.findByEmail(email).
                orElseThrow(
                        ()->new ResourceNotFoundException("record not found with","email",email)
                );
        CustomerResponseDto customerResponseDto=CustomerDetailsMapper.CustomerDetailsToCustomerResponseDto(customerDetails,new CustomerResponseDto());
        return customerResponseDto;
    }

    /**
     * @param customerRequestDto
     * @return
     */
    @Override
    public Boolean updateCustomerDetailsRecord(CustomerRequestDto customerRequestDto) {

        CustomerDetails customerDetails=customerDetailsRepo.findByEmail(customerRequestDto.getEmail()).
                orElseThrow(
                        ()->new ResourceNotFoundException("record not found with","email",customerRequestDto.getEmail())
                );

        if (ChronoUnit.YEARS.between(customerRequestDto.getDob(), LocalDate.now()) < 18) {
            throw new CustomerAgeException("The customer must be older than 18");
        }
        CustomerGroup customerGroup=getCustomerGroup(customerRequestDto);
        Optional<CustomerDetails> customerWithSameConstraint=customerDetailsRepo.findByOccupationAndDobAndCustomerGroup(customerRequestDto.getOccupation(),customerRequestDto.getDob(),customerGroup);
        if(customerWithSameConstraint.isPresent() && !customerWithSameConstraint.get().equals(customerDetails))
            throw new CustomerDetailsAlreadyExistsException("The customer with occupation "+customerRequestDto.getOccupation()+" ,date of birth "+customerRequestDto.getDob()+" and Customer group "+customerGroup+" already exist");

        CustomerDetailsMapper.CustomerRequestDtoToCustomerDetails(customerRequestDto, customerDetails);
        customerDetails.setCustomerGroup(customerGroup);

        customerDetailsRepo.save(customerDetails);
        return true;
    }

    /**
     * @param email
     * @return
     */
    @Override
    public Boolean deleteCustomerDetailsByEmail(String email) {
        CustomerDetails customerDetails=customerDetailsRepo.findByEmail(email).
                orElseThrow(
                        ()->new ResourceNotFoundException("record not found with","email",email)
                );
        customerDetailsRepo.delete(customerDetails);
        return true;
    }
}
