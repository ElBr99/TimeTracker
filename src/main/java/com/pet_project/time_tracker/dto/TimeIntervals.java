package com.pet_project.time_tracker.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeIntervals {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "d HH:mm")
    LocalDateTime startedAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "d HH:mm")
    LocalDateTime finishedAt;

    String name;
}
