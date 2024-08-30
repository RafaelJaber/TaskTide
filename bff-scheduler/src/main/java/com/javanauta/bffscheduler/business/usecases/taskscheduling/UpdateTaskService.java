package com.javanauta.bffscheduler.business.usecases.taskscheduling;


import com.javanauta.bffscheduler.business.dto.request.TaskRequestDTO;
import com.javanauta.bffscheduler.business.dto.response.TaskResponseDTO;
import com.javanauta.bffscheduler.infrastructure.client.TaskClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateTaskService {

    private final TaskClient client;

    public TaskResponseDTO execute(TaskRequestDTO dto, String taskId, String token) {
        return client.update(taskId, dto, token);
    }
}
