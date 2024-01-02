package com.arjunagi.customer_details_management_service.dtos;

import com.arjunagi.customer_details_management_service.models.CustomerGroup;
import com.arjunagi.customer_details_management_service.models.Occupation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDto {
    private String name;

    private String email;

    private LocalDate dob;
    private Occupation occupation;
    private CustomerGroup customerGroup;

}
