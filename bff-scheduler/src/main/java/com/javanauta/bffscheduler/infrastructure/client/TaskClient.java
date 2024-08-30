package com.javanauta.bffscheduler.infrastructure.client;

import com.javanauta.bffscheduler.business.dto.request.TaskRequestDTO;
import com.javanauta.bffscheduler.business.dto.request.TaskStatusRequestDTO;
import com.javanauta.bffscheduler.business.dto.response.TaskResponseDTO;
import com.javanauta.bffscheduler.core.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "task-scheduler-microservice", path = "/tasks", configuration = FeignConfig.class)
public interface TaskClient {

    @GetMapping("/events")
    List<TaskResponseDTO> findTasksBySchedulingDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,
            @RequestHeader("Authorization") String token
    );

    @GetMapping("/my")
    List<TaskResponseDTO> findTasksByCurrentUser(@RequestHeader("Authorization") String token);

    @PostMapping
    TaskResponseDTO insert(@RequestBody TaskRequestDTO request,
                           @RequestHeader("Authorization") String token);

    @PutMapping("/{taskId}")
    TaskResponseDTO update(
            @PathVariable("taskId") String taskId,
            @RequestBody TaskRequestDTO request,
            @RequestHeader("Authorization") String token
    );

    @PatchMapping("/{taskId}/change-status")
    TaskResponseDTO changeStatus(
            @PathVariable("taskId") String taskId,
            @RequestBody TaskStatusRequestDTO request,
            @RequestHeader("Authorization") String token
    );

    @DeleteMapping("/{taskId}")
    Void delete(@PathVariable("taskId") String taskId,
                @RequestHeader("Authorization") String token);
}
