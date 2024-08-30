package com.javanauta.bffscheduler.business.dto.request;

import com.javanauta.bffscheduler.infrastructure.enums.TaskStatusEnum;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskStatusRequestDTO {
    private TaskStatusEnum status;
}
