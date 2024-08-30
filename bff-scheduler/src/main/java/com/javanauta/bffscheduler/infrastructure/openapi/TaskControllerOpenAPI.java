package com.javanauta.bffscheduler.infrastructure.openapi;

import com.javanauta.bffscheduler.business.dto.request.TaskRequestDTO;
import com.javanauta.bffscheduler.business.dto.request.TaskStatusRequestDTO;
import com.javanauta.bffscheduler.business.dto.response.TaskResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "Task API", description = "API for managing tasks in the scheduling system")
public interface TaskControllerOpenAPI {

    @Operation(summary = "Find tasks by scheduling date", description = "Retrieve tasks scheduled within a specific date and time range for the logged-in user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Tasks retrieved successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskResponseDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid date range provided",
                            content = @Content),
                    @ApiResponse(responseCode = "401", description = "Invalid or expired JWT token",
                            content = @Content)
            })
    @GetMapping("/events")
    ResponseEntity<List<TaskResponseDTO>> findTasksBySchedulingDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,
            @RequestHeader("Authorization") String token
    );

    @Operation(summary = "Find tasks of the current user", description = "Retrieve all tasks created by the logged-in user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Tasks retrieved successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskResponseDTO.class))),
                    @ApiResponse(responseCode = "401", description = "Invalid or expired JWT token",
                            content = @Content)
            })
    @GetMapping("/my")
    ResponseEntity<List<TaskResponseDTO>> findTasksByCurrentUser(@RequestHeader("Authorization") String token);

    @Operation(summary = "Create a new task", description = "Create a new task for the logged-in user",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Task created successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskResponseDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid data provided",
                            content = @Content),
                    @ApiResponse(responseCode = "401", description = "Invalid or expired JWT token",
                            content = @Content)
            })
    @PostMapping
    ResponseEntity<TaskResponseDTO> insert(@RequestBody TaskRequestDTO request,
                                           @RequestHeader("Authorization") String token);

    @Operation(summary = "Update an existing task", description = "Update the details of an existing task for the logged-in user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Task updated successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskResponseDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid data provided",
                            content = @Content),
                    @ApiResponse(responseCode = "401", description = "Invalid or expired JWT token",
                            content = @Content),
                    @ApiResponse(responseCode = "404", description = "Task not found",
                            content = @Content)
            })
    @PutMapping("/{taskId}")
    ResponseEntity<TaskResponseDTO> update(@PathVariable String taskId,
                                           @RequestBody TaskRequestDTO request,
                                           @RequestHeader("Authorization") String token);

    @Operation(summary = "Change the status of an existing task", description = "Change the status of a task (e.g., to completed) for the logged-in user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Task status changed successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TaskResponseDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid data provided",
                            content = @Content),
                    @ApiResponse(responseCode = "401", description = "Invalid or expired JWT token",
                            content = @Content),
                    @ApiResponse(responseCode = "404", description = "Task not found",
                            content = @Content)
            })
    @PatchMapping("/{taskId}/change-status")
    ResponseEntity<TaskResponseDTO> changeStatus(@PathVariable String taskId,
                                                 @RequestBody TaskStatusRequestDTO request,
                                                 @RequestHeader("Authorization") String token);

    @Operation(summary = "Delete an existing task", description = "Delete an existing task for the logged-in user by task ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Task deleted successfully"),
                    @ApiResponse(responseCode = "401", description = "Invalid or expired JWT token",
                            content = @Content),
                    @ApiResponse(responseCode = "404", description = "Task not found",
                            content = @Content)
            })
    @DeleteMapping("/{taskId}")
    ResponseEntity<Void> delete(@PathVariable String taskId,
                                @RequestHeader("Authorization") String token);
}
