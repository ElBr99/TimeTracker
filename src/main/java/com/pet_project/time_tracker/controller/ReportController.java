package com.pet_project.time_tracker.controller;

import com.pet_project.time_tracker.dto.DatePeriodDto;
import com.pet_project.time_tracker.dto.TasksByTime;
import com.pet_project.time_tracker.dto.TimeIntervals;
import com.pet_project.time_tracker.dto.WorkedHours;
import com.pet_project.time_tracker.service.StatisticsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/reports")
@RequiredArgsConstructor
public class ReportController {
    private final StatisticsServiceImpl statisticsService;


    @GetMapping({"/getTimeConsumed"})
    public List<TasksByTime> getTimeConsumedByTasks(@RequestParam Integer userId) {
        return statisticsService.timeConsumedByTasks(userId);
    }

    @GetMapping({"/getWorkingTimeIntervals"})
    public List<TimeIntervals> getTimePeriods(@ModelAttribute DatePeriodDto datePeriodDto, @RequestParam Integer userId) {
        return statisticsService.getTimePeriods(datePeriodDto, userId);
    }


    @GetMapping({"/getWorkedHours"})
    public WorkedHours getWorkedHours(@ModelAttribute DatePeriodDto datePeriodDto, @RequestParam Integer userId) {
        return statisticsService.getWorkedHours(datePeriodDto, userId);
    }


}
