package com.pet_project.time_tracker.service;

import com.pet_project.time_tracker.dto.DatePeriodDto;
import com.pet_project.time_tracker.dto.TasksByTime;
import com.pet_project.time_tracker.dto.TimeIntervals;
import com.pet_project.time_tracker.dto.WorkedHours;
import com.pet_project.time_tracker.projections.TasksByTimeProjection;
import com.pet_project.time_tracker.projections.TimeIntervalsByTasks;
import com.pet_project.time_tracker.projections.WorkedHoursProjection;
import com.pet_project.time_tracker.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Transactional
@Component
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final TaskRepository taskRepository;

    @Override
    public List<TasksByTime> timeConsumedByTasks(Integer userId) {
        List<TasksByTimeProjection> tasksByTimeProjectionList = taskRepository.findByUserId(userId);
        List<TasksByTime> tasksByTimeList = new ArrayList<>();

        tasksByTimeProjectionList.forEach(tasksByTimeProjection -> {

            TasksByTime tasksByTime = TasksByTime.builder()
                    .id(tasksByTimeProjection.getId())
                    .name(tasksByTimeProjection.getName())
                    .days(tasksByTimeProjection.getDaysSpent())
                    .minutes(tasksByTimeProjection.getMinutesSpent())
                    .build();

            tasksByTimeList.add(tasksByTime);
        });

        return tasksByTimeList;
    }

    @Override
    public List<TimeIntervals> getTimePeriods(DatePeriodDto datePeriodDto, Integer userId) {
        List<TimeIntervalsByTasks> timeIntervalsByTasks = taskRepository.findByUserIdAndDate(OffsetDateTime.of(datePeriodDto.getFrom().atStartOfDay(), ZoneOffset.UTC),
                OffsetDateTime.of(datePeriodDto.getTo().atStartOfDay(), ZoneOffset.UTC), userId);

        List<TimeIntervals> listForAdding = new ArrayList<>();
        List<TimeIntervals> occupiedIntervals = new ArrayList<>();

        timeIntervalsByTasks.forEach(task -> {
            TimeIntervals timeInterval = TimeIntervals.builder()
                    .startedAt(task.getStartedAt())
                    .finishedAt(task.getFinishedAt())
                    .name(task.getName())
                    .build();
            occupiedIntervals.add(timeInterval);
        });


        occupiedIntervals.sort(Comparator.comparing(TimeIntervals::getStartedAt));


        LocalDateTime currentStart = datePeriodDto.getFrom().atStartOfDay();
        for (TimeIntervals interval : occupiedIntervals) {
            if (currentStart.isBefore(interval.getStartedAt())) {
                TimeIntervals gap = TimeIntervals.builder()
                        .startedAt(currentStart)
                        .finishedAt(interval.getStartedAt())
                        .name("Нерабочее время")
                        .build();
                listForAdding.add(gap);
            }

            currentStart = interval.getFinishedAt();
        }


        if (currentStart.isBefore(datePeriodDto.getTo().atStartOfDay())) {
            TimeIntervals gap = TimeIntervals.builder()
                    .startedAt(currentStart)
                    .finishedAt(datePeriodDto.getTo().atStartOfDay())
                    .name("Нерабочее время")
                    .build();
            listForAdding.add(gap);
        }

        listForAdding.addAll(occupiedIntervals);

        return listForAdding;

    }

    @Override
    public WorkedHours getWorkedHours(DatePeriodDto datePeriodDto, Integer userId) {
        WorkedHoursProjection workedHoursProjection = taskRepository.getWorkedHours(
                OffsetDateTime.of(datePeriodDto.getFrom().atStartOfDay(), ZoneOffset.UTC),
                OffsetDateTime.of(datePeriodDto.getTo().atStartOfDay(), ZoneOffset.UTC), userId);

        return WorkedHours.builder()
                .workedDays(workedHoursProjection.getWorkedDays())
                .workedHours(workedHoursProjection.getWorkedHours())
                .workedMinutes(workedHoursProjection.getWorkedMinutes())
                .build();


    }
}
