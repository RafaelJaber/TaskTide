package com.javanauta.taskscheduler.controller;

import com.javanauta.taskscheduler.business.dto.request.TaskRequestDTO;
import com.javanauta.taskscheduler.business.dto.response.TaskResponseDTO;
import com.javanauta.taskscheduler.business.usecases.CreateTaskService;
import com.javanauta.taskscheduler.business.usecases.DeleteTaskByIdService;
import com.javanauta.taskscheduler.business.usecases.FindTasksByCurrentUserService;
import com.javanauta.taskscheduler.business.usecases.FindTasksBySchedulingDateService;
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
public class TaskController {

    private final FindTasksBySchedulingDateService findTasksBySchedulingDateService;
    private final FindTasksByCurrentUserService findTasksByCurrentUserService;
    private final CreateTaskService createTaskService;
    private final DeleteTaskByIdService deleteTaskByIdService;


    @GetMapping("/events")
    public ResponseEntity<List<TaskResponseDTO>> findTasksBySchedulingDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to
    ) {
        List<TaskResponseDTO> result = this.findTasksBySchedulingDateService.execute(from, to);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/my")
    public ResponseEntity<List<TaskResponseDTO>> findTasksByCurrentUser() {
        List<TaskResponseDTO> result = this.findTasksByCurrentUserService.execute();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> insert(@RequestBody TaskRequestDTO request) {
        TaskResponseDTO created = this.createTaskService.execute(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> delete(@PathVariable String taskId) {
        this.deleteTaskByIdService.execute(taskId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
