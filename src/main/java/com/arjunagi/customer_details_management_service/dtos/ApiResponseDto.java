package com.arjunagi.customer_details_management_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseDto {
    private String statusCode;
    private String statusMessage;

}
