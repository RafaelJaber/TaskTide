package com.javanauta.taskscheduler.business.usecases;

import com.javanauta.taskscheduler.business.dto.response.TaskResponseDTO;
import com.javanauta.taskscheduler.business.mapper.TaskMapper;
import com.javanauta.taskscheduler.infrastructure.entities.TaskEntity;
import com.javanauta.taskscheduler.infrastructure.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FindTasksBySchedulingDateService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Transactional(readOnly = true)
    public List<TaskResponseDTO> execute(LocalDateTime from , LocalDateTime to) {
        List<TaskEntity> tasks = taskRepository.findBySchedulingDateBetween(from, to);
        return tasks.stream().map(taskMapper::toTaskResponseDTO).toList();
    }
}
