package com.javanauta.taskscheduler.business.dto.request;

import com.javanauta.taskscheduler.infrastructure.enums.TaskStatusEnum;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskStatusRequestDTO {
    private TaskStatusEnum status;
}
