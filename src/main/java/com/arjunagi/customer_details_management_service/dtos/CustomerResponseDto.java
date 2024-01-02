package com.arjunagi.customer_details_management_service.dtos;

import com.arjunagi.customer_details_management_service.models.CustomerGroup;
import com.arjunagi.customer_details_management_service.models.Occupation;


import java.time.LocalDate;

public class CustomerResponseDto {
    private String name;

    private String email;

    private LocalDate dob;
    private Occupation occupation;
    private CustomerGroup customerGroup;

}
