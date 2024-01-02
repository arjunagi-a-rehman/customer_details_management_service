package com.arjunagi.customer_details_management_service.controller;

import com.arjunagi.customer_details_management_service.dtos.ApiResponseDto;
import com.arjunagi.customer_details_management_service.dtos.CustomerRequestDto;
import com.arjunagi.customer_details_management_service.dtos.CustomerResponseDto;
import com.arjunagi.customer_details_management_service.dtos.ErrorResponseDto;
import com.arjunagi.customer_details_management_service.services.ICustomerDetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CURD API's for Customer Details",
        description = "CURD REST API's in Customer Details Management Service to Create, Read, Update and Update Customer Details "
)
@RestController
@RequestMapping(path = "/api",produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class CustomerDetailsController {

    private ICustomerDetailService customerDetailService;


    @Operation(
            summary = "Create Customer Details Record REST API",
            description = "API to create new Customer details from the input data provided by user"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PostMapping("/customer")
    public ResponseEntity<ApiResponseDto> createCustomerDetails(@RequestBody @Valid CustomerRequestDto customerRequestDto){
        customerDetailService.createCustomerDetailsRecord(customerRequestDto);
        return new ResponseEntity<>(new ApiResponseDto("201","created customer record successfully"), HttpStatus.CREATED);
    }

//---------------------------------------------------------------------------------------------------------------------------------------------------------------

    @Operation(
            summary = "Fetch Customer Details REST API",
            description = "API to Fetch Customer Details based on email"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/customer")
    public ResponseEntity<CustomerResponseDto> getCustomerDetailsByEmail(@RequestParam @Email(message = "Invalid email format") String email){
        return new ResponseEntity<>(customerDetailService.getCustomerDetailsByEmail(email),HttpStatus.OK);
    }


//--------------------------------------------------------------------------------------------------------------------------------------------------------------

    @Operation(
            summary = "Update Customer Details REST API",
            description = "API to update  Customer Details based on email Note:- email cannot be changed also combination of occupation and date of birth and customer group must be unique"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PutMapping("/customer")
    public ResponseEntity<ApiResponseDto> updateCustomerDetails(@RequestBody @Valid CustomerRequestDto customerRequestDto){
        Boolean isUpdated=customerDetailService.updateCustomerDetailsRecord(customerRequestDto);
        if(isUpdated)
            return ResponseEntity.ok(new ApiResponseDto("200","updated successfully"));
        else
            return new ResponseEntity<>(new ApiResponseDto("500","Something went wrong please try later or contact admin"),HttpStatus.INTERNAL_SERVER_ERROR);
    }


//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    @Operation(
            summary = "Delete Customer Details REST API",
            description = "API to Delete Customer Details based on email"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @DeleteMapping("/customer/{email}")
    public ResponseEntity<ApiResponseDto> deleteCustomerByEmail(@PathVariable @Email(message = "Invalid email format") String email){
        Boolean isDeleted=customerDetailService.deleteCustomerDetailsByEmail(email);
        if(isDeleted)
            return ResponseEntity.ok(new ApiResponseDto("200","deleted successfully"));
        else
            return new ResponseEntity<>(new ApiResponseDto("500","Something went wrong please try later or contact admin"),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
