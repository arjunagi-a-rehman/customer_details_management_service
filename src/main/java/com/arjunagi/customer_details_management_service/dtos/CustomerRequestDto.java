package com.arjunagi.customer_details_management_service.dtos;

import com.arjunagi.customer_details_management_service.models.Occupation;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequestDto {
    private String name;
    private String email;
    private LocalDate dob;
    private Occupation occupation;

}
