package com.javanauta.taskscheduler.business.usecases;

import com.javanauta.taskscheduler.business.dto.response.TaskResponseDTO;
import com.javanauta.taskscheduler.business.mapper.TaskMapper;
import com.javanauta.taskscheduler.core.utils.JwtUtil;
import com.javanauta.taskscheduler.infrastructure.entities.TaskEntity;
import com.javanauta.taskscheduler.infrastructure.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindTasksByCurrentUserService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final JwtUtil jwtUtil;

    @Transactional(readOnly = true)
    public List<TaskResponseDTO> execute() {
        String userEmail = jwtUtil.getCurrentUserEmail();
        List<TaskEntity> tasks = this.taskRepository.findByUserEmail(userEmail);

        return tasks.stream().map(taskMapper::toTaskResponseDTO).toList();
    }
}
