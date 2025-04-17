package com.pet_project.time_tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class WorkedHours {

    int workedDays;
    int workedHours;
    int workedMinutes;

}
