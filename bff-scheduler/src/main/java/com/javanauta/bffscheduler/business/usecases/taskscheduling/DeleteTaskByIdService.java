package com.javanauta.bffscheduler.business.usecases.taskscheduling;


import com.javanauta.bffscheduler.infrastructure.client.TaskClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteTaskByIdService {

    private final TaskClient client;

    public Void execute(String taskId, String token) {
        return client.delete(taskId, token);
    }
}
