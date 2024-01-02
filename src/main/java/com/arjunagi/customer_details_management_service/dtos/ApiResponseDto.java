package com.arjunagi.customer_details_management_service.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "API Response",
        description = "Schema holds the API Response"
)
public class ApiResponseDto {
    @Schema(
            description = "status code",
            example = "200"
    )
    private String statusCode;
    @Schema(
            description = "status message",
            example = "HTTP Response OK"
    )
    private String statusMessage;

}
