package com.javanauta.taskscheduler.business.usecases;

import com.javanauta.taskscheduler.business.dto.request.TaskStatusRequestDTO;
import com.javanauta.taskscheduler.business.dto.response.TaskResponseDTO;
import com.javanauta.taskscheduler.business.mapper.TaskMapper;
import com.javanauta.taskscheduler.infrastructure.entities.TaskEntity;
import com.javanauta.taskscheduler.infrastructure.exceptions.ResourceNotFoundException;
import com.javanauta.taskscheduler.infrastructure.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChangeTaskStatusService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Transactional
    public TaskResponseDTO execute(TaskStatusRequestDTO dto, String id) {
        TaskEntity task = taskRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Task with id " + id + " not found")
        );
        task.setStatus(dto.getStatus());
        taskRepository.save(task);
        return taskMapper.toTaskResponseDTO(task);
    }
}
