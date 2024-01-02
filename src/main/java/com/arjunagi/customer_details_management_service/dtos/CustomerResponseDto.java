package com.arjunagi.customer_details_management_service.dtos;

import com.arjunagi.customer_details_management_service.models.CustomerGroup;
import com.arjunagi.customer_details_management_service.models.Occupation;
import io.swagger.v3.oas.annotations.media.Schema;
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
public class CustomerResponseDto {
    @Schema(
            description = "customer name"
            ,example = "champu das"
    )
    private String name;

    @Schema(
            description = "customer email"
            ,example = "champu123das@yahoo.com"
    )
    private String email;

    @Schema(
            description = "customer date of birth"
            ,example = "2000-01-02"
    )
    private LocalDate dob;

    @Schema(
            description = "customer occupation from [Developer, Chef, Plumber, Carpenter, Other]"
            ,example = "Developer"
    )
    private Occupation occupation;

    @Schema(
            description = "customers customerGroup[Hikeon, Chef, Developer, NA]"
            ,example = "Developer"
    )
    private CustomerGroup customerGroup;

}
