package com.pet_project.time_tracker.service;

import com.pet_project.time_tracker.dto.DatePeriodDto;
import com.pet_project.time_tracker.dto.TasksByTime;
import com.pet_project.time_tracker.dto.TimeIntervals;
import com.pet_project.time_tracker.dto.WorkedHours;
import com.pet_project.time_tracker.projections.TimeIntervalsByTasks;


import java.util.List;

public interface StatisticsService {

    List<TasksByTime> timeConsumedByTasks (Integer userId);
    List<TimeIntervals> getTimePeriods (DatePeriodDto datePeriodDto, Integer userId);
    WorkedHours getWorkedHours (DatePeriodDto datePeriodDto, Integer userId);
}
