package com.javanauta.taskscheduler.business.usecases;

import com.javanauta.taskscheduler.business.dto.request.TaskRequestDTO;
import com.javanauta.taskscheduler.business.dto.response.TaskResponseDTO;
import com.javanauta.taskscheduler.business.mapper.TaskMapper;
import com.javanauta.taskscheduler.business.mapper.TaskUpdateMapper;
import com.javanauta.taskscheduler.core.utils.JwtUtil;
import com.javanauta.taskscheduler.infrastructure.entities.TaskEntity;
import com.javanauta.taskscheduler.infrastructure.exceptions.OperationNotPermittedException;
import com.javanauta.taskscheduler.infrastructure.exceptions.ResourceNotFoundException;
import com.javanauta.taskscheduler.infrastructure.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UpdateTaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final TaskUpdateMapper taskUpdateMapper;
    private final JwtUtil jwtUtil;

    @Transactional
    public TaskResponseDTO execute(TaskRequestDTO dto, String taskId) {
        TaskEntity task = taskRepository.findById(taskId).orElseThrow(
                () -> new ResourceNotFoundException("Task with id " + taskId + " not found")
        );
        String userEmail = this.jwtUtil.getCurrentUserEmail();
        if (!Objects.equals(userEmail, task.getUserEmail())) {
            throw new OperationNotPermittedException("You do not have permission to update this task");
        }
        taskUpdateMapper.updateTask(dto, task);
        TaskEntity updated = taskRepository.save(task);
        return this.taskMapper.toTaskResponseDTO(updated);
    }
}
