package com.javanauta.taskscheduler.business.dto.response;

import com.javanauta.taskscheduler.infrastructure.enums.TaskStatusEnum;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponseDTO {
    private String id;
    private String taskName;
    private String description;
    private LocalDateTime schedulingDate;
    private String userEmail;
    private TaskStatusEnum status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
