package com.pet_project.time_tracker.dto;


import lombok.*;
import lombok.experimental.FieldNameConstants;

@Value
@FieldNameConstants
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
@Data
public class CreateTaskDto {

    String name;

    String description;

}
