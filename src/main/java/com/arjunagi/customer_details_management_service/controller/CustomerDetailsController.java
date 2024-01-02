package com.arjunagi.customer_details_management_service.controller;

import com.arjunagi.customer_details_management_service.dtos.ApiResponseDto;
import com.arjunagi.customer_details_management_service.dtos.CustomerRequestDto;
import com.arjunagi.customer_details_management_service.dtos.CustomerResponseDto;
import com.arjunagi.customer_details_management_service.services.ICustomerDetailService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api",produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class CustomerDetailsController {

    private ICustomerDetailService customerDetailService;
    @PostMapping("/customer")
    public ResponseEntity<ApiResponseDto> createCustomerDetails(@RequestBody CustomerRequestDto customerRequestDto){
        customerDetailService.createCustomerDetailsRecord(customerRequestDto);
        return new ResponseEntity<>(new ApiResponseDto("201","created customer record successfully"), HttpStatus.CREATED);
    }

    @GetMapping("/customer")
    public ResponseEntity<CustomerResponseDto> getCustomerDetailsByEmail(@RequestParam String email){
        return new ResponseEntity<>(customerDetailService.getCustomerDetailsByEmail(email),HttpStatus.OK);
    }

    @PutMapping("/customer")
    public ResponseEntity<ApiResponseDto> updateCustomerDetails(@RequestBody CustomerRequestDto customerRequestDto){
        Boolean isUpdated=customerDetailService.updateCustomerDetailsRecord(customerRequestDto);
        if(isUpdated)
            return ResponseEntity.ok(new ApiResponseDto("200","updated successfully"));
        else
            return new ResponseEntity<>(new ApiResponseDto("500","Something went wrong please try later or contact admin"),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/customer/{email}")
    public ResponseEntity<ApiResponseDto> deleteCustomerByEmail(@PathVariable String email){
        Boolean isDeleted=customerDetailService.deleteCustomerDetailsByEmail(email);
        if(isDeleted)
            return ResponseEntity.ok(new ApiResponseDto("200","deleted successfully"));
        else
            return new ResponseEntity<>(new ApiResponseDto("500","Something went wrong please try later or contact admin"),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
