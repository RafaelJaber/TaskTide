package com.javanauta.taskscheduler.controller;

import com.javanauta.taskscheduler.business.dto.request.TaskRequestDTO;
import com.javanauta.taskscheduler.business.dto.response.TaskResponseDTO;
import com.javanauta.taskscheduler.business.usecases.CreateTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final CreateTaskService createTaskService;


    @PostMapping
    public ResponseEntity<TaskResponseDTO> insert(@RequestBody TaskRequestDTO request) {
        TaskResponseDTO created = this.createTaskService.execute(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).body(created);
    }
}
