package com.arjunagi.customer_details_management_service.dtos;

import com.arjunagi.customer_details_management_service.models.Occupation;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;

public class CustomerRequestDto {
    private String name;
    private String email;
    private LocalDate dob;
    private Occupation occupation;

}
