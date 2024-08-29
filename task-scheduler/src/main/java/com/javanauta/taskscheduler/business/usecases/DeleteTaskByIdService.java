package com.javanauta.taskscheduler.business.usecases;

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
public class DeleteTaskByIdService {

    private final TaskRepository taskRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public void execute(String taskId) {
        TaskEntity task = this.taskRepository.findById(taskId).orElseThrow(
                () -> new ResourceNotFoundException("Task with id " + taskId + " does not exist")
        );
        String userEmail = this.jwtUtil.getCurrentUserEmail();
        if (!Objects.equals(userEmail, task.getUserEmail())) {
            throw new OperationNotPermittedException("You do not have permission to delete a task");
        }

        this.taskRepository.deleteById(taskId);
    }
}
