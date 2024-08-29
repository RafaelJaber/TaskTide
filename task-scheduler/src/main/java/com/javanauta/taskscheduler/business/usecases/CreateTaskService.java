package com.javanauta.taskscheduler.business.usecases;

import com.javanauta.taskscheduler.business.dto.request.TaskRequestDTO;
import com.javanauta.taskscheduler.business.dto.response.TaskResponseDTO;
import com.javanauta.taskscheduler.business.mapper.TaskMapper;
import com.javanauta.taskscheduler.core.utils.JwtUtil;
import com.javanauta.taskscheduler.infrastructure.entities.TaskEntity;
import com.javanauta.taskscheduler.infrastructure.enums.TaskStatusEnum;
import com.javanauta.taskscheduler.infrastructure.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateTaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final JwtUtil jwtUtil;

    @Transactional
    public TaskResponseDTO execute(TaskRequestDTO dto) {
        TaskEntity taskEntity = taskMapper.toTaskEntity(dto);
        taskEntity.setStatus(TaskStatusEnum.PENDING);
        taskEntity.setUserEmail(jwtUtil.getCurrentUserEmail());
        TaskEntity insetedTaskEntity = taskRepository.save(taskEntity);

        return taskMapper.toTaskResponseDTO(insetedTaskEntity);
    }
}
