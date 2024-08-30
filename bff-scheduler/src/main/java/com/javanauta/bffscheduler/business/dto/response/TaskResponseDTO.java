package com.javanauta.bffscheduler.business.dto.response;

import com.javanauta.bffscheduler.infrastructure.enums.TaskStatusEnum;
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
