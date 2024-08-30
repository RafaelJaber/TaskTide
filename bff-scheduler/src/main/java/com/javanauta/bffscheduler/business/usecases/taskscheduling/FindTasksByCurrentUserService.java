package com.javanauta.bffscheduler.business.usecases.taskscheduling;


import com.javanauta.bffscheduler.business.dto.response.TaskResponseDTO;
import com.javanauta.bffscheduler.infrastructure.client.TaskClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindTasksByCurrentUserService {

    private final TaskClient client;

    public List<TaskResponseDTO> execute(String token) {
        return client.findTasksByCurrentUser(token);
    }
}
