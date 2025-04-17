package com.pet_project.time_tracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldNameConstants;

@Value
@FieldNameConstants
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
@Data
public class CreateUserDto {

    @NotNull
    @Size(min = 3, max = 64)
    @NotBlank
    String name;

    @NotNull
    @Size(min = 3, max = 64)
    @NotBlank
    String lastName;


}
