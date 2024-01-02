package com.arjunagi.customer_details_management_service.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(
        name = "ErrorResponse",
        description = "Schema holds the Error Response for diagnostics purposes "
)
public class ErrorResponseDto {
    @Schema(
            description = "api path"
            ,example = "/api/customer"
    )
    private String apiPath;
    @Schema(
            description = "error code",
            example = "500"
    )
    private HttpStatus errorCode;
    @Schema(
            description = "error message",
            example = "resource not found"
    )
    private String errorMessage;
    private LocalDateTime errorTime;
}
