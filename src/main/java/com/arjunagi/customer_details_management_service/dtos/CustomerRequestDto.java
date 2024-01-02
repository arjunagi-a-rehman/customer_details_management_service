package com.arjunagi.customer_details_management_service.dtos;

import com.arjunagi.customer_details_management_service.models.Occupation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "Customer request",
        description = "Schema holds the customer details provided by user "
)
public class CustomerRequestDto {
    @Schema(
            description = "customer name"
            ,example = "champu das"
    )
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Schema(
            description = "customer email"
            ,example = "champu123das@yahoo.com"
    )
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @Schema(
            description = "customer date of birth"
            ,example = "2000-01-02"
    )
    @NotNull(message = "Date of birth cannot be null")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dob;

    @Schema(
            description = "customer occupation from [Developer, Chef, Plumber, Carpenter, Other]"
            ,example = "Developer"
    )
    @NotNull(message = "Occupation cannot be null")
    private Occupation occupation;

}
