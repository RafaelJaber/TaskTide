package com.javanauta.bffscheduler.business.usecases.taskscheduling;


import com.javanauta.bffscheduler.business.dto.request.TaskStatusRequestDTO;
import com.javanauta.bffscheduler.business.dto.response.TaskResponseDTO;
import com.javanauta.bffscheduler.infrastructure.client.TaskClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangeTaskStatusService {

    private final TaskClient client;

    public TaskResponseDTO execute(TaskStatusRequestDTO dto, String id, String token) {
       return client.changeStatus(id, dto, token);
    }
}
