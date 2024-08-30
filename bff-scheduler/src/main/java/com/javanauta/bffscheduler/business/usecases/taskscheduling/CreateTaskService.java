package com.javanauta.bffscheduler.business.usecases.taskscheduling;


import com.javanauta.bffscheduler.business.dto.request.TaskRequestDTO;
import com.javanauta.bffscheduler.business.dto.response.TaskResponseDTO;
import com.javanauta.bffscheduler.infrastructure.client.TaskClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateTaskService {

    private final TaskClient client;

    public TaskResponseDTO execute(TaskRequestDTO dto, String token) {
       return client.insert(dto, token);
    }
}
