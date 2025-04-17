package com.pet_project.time_tracker.repository;

import com.pet_project.time_tracker.model.Task;
import com.pet_project.time_tracker.projections.TasksByTimeProjection;
import com.pet_project.time_tracker.projections.TimeIntervalsByTasks;
import com.pet_project.time_tracker.projections.WorkedHoursProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;


@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {


    @Query(value = "select id , name, extract (MINUTE from timeSpent) as minutesSpent, EXTRACT(DAY from timeSpent) as daysSpent from\n" +
            "(select t.id, t.name, t.finished_At-t.started_At as timeSpent from tasks t \n" +
            "where t.user_id = :userId and t.status = 'COMPLETED' order by timeSpent desc) AS subquery", nativeQuery = true)
    List<TasksByTimeProjection> findByUserId(Integer userId);


    @Query(value = "select t.started_At, t.finished_At, t.name from tasks t where t.user_id =:userId and t.started_At>=:from and t.finished_At<=:to", nativeQuery = true)
    List<TimeIntervalsByTasks> findByUserIdAndDate(OffsetDateTime from, OffsetDateTime to, Integer userId);


    @Query(value = "select \n" +
            "extract (Day from total_time) as workedDays, \n" +
            "extract (Hour from total_time) as workedHours,\n" +
            "extract (Minute from total_time) as workedMinutes\n" +
            "from\n" +
            "(select sum (timeSpent) as total_time from\n" +
            "(select t.finished_At-t.started_At as timeSpent from tasks t \n" +
            "where t.user_id = :userId and t.status = 'COMPLETED' order by timeSpent desc))", nativeQuery = true)
    WorkedHoursProjection getWorkedHours(OffsetDateTime from, OffsetDateTime to, Integer userId);


}
