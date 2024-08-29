package com.javanauta.taskscheduler.infrastructure.repositories;

import com.javanauta.taskscheduler.infrastructure.entities.TaskEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<TaskEntity, String> {

    List<TaskEntity> findBySchedulingDateBetween(LocalDateTime from, LocalDateTime to);

    List<TaskEntity> findByUserEmail(String email);
}
