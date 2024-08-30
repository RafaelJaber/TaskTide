package com.javanauta.bffscheduler.controller;

import com.javanauta.bffscheduler.business.dto.request.TaskRequestDTO;
import com.javanauta.bffscheduler.business.dto.request.TaskStatusRequestDTO;
import com.javanauta.bffscheduler.business.dto.response.TaskResponseDTO;
import com.javanauta.bffscheduler.business.usecases.taskscheduling.*;
import com.javanauta.bffscheduler.infrastructure.openapi.TaskControllerOpenAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController implements TaskControllerOpenAPI {

    private final FindPendingTasksBySchedulingDateService findPendingTasksBySchedulingDateService;
    private final FindTasksByCurrentUserService findTasksByCurrentUserService;
    private final CreateTaskService createTaskService;
    private final UpdateTaskService updateTaskService;
    private final ChangeTaskStatusService changeTaskStatusService;
    private final DeleteTaskByIdService deleteTaskByIdService;


    @GetMapping("/events")
    public ResponseEntity<List<TaskResponseDTO>> findTasksBySchedulingDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,
            @RequestHeader("Authorization") String token
    ) {
        List<TaskResponseDTO> result = this.findPendingTasksBySchedulingDateService.execute(from, to, token);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/my")
    public ResponseEntity<List<TaskResponseDTO>> findTasksByCurrentUser(@RequestHeader("Authorization") String token) {
        List<TaskResponseDTO> result = this.findTasksByCurrentUserService.execute(token);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> insert(@RequestBody TaskRequestDTO request,
                                                  @RequestHeader("Authorization") String token) {
        TaskResponseDTO created = this.createTaskService.execute(request, token);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskResponseDTO> update(
            @PathVariable String taskId,
            @RequestBody TaskRequestDTO request,
            @RequestHeader("Authorization") String token
    ) {
        TaskResponseDTO result = this.updateTaskService.execute(request, taskId, token);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PatchMapping("/{taskId}/change-status")
    public ResponseEntity<TaskResponseDTO> changeStatus(
            @PathVariable String taskId,
            @RequestBody TaskStatusRequestDTO request,
            @RequestHeader("Authorization") String token
    ) {
        TaskResponseDTO result = this.changeTaskStatusService.execute(request, taskId, token);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> delete(@PathVariable String taskId,
                                       @RequestHeader("Authorization") String token) {
        this.deleteTaskByIdService.execute(taskId, token);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
