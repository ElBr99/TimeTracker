package com.pet_project.time_tracker.dto;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldNameConstants;

@Value
@FieldNameConstants
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
@Data
public class UpdateInfoDto {

    @Size(min=3, max=64)
    String name;

    @Size(min=3, max=64)
    String lastName;

}
