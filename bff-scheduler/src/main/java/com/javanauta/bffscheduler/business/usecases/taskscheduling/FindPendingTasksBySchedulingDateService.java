package com.javanauta.bffscheduler.business.usecases.taskscheduling;


import com.javanauta.bffscheduler.business.dto.response.TaskResponseDTO;
import com.javanauta.bffscheduler.infrastructure.client.TaskClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FindPendingTasksBySchedulingDateService {

    private final TaskClient client;

    public List<TaskResponseDTO> execute(LocalDateTime from , LocalDateTime to, String token) {
        return client.findTasksBySchedulingDate(from, to, token);
    }
}
