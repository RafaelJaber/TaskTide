package com.javanauta.taskscheduler.infrastructure.repositories;

import com.javanauta.taskscheduler.infrastructure.entities.TaskEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends MongoRepository<TaskEntity, String> {
}
