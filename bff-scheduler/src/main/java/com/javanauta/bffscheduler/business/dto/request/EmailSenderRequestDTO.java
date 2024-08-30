package com.javanauta.bffscheduler.business.dto.request;

import com.javanauta.bffscheduler.infrastructure.enums.TaskStatusEnum;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailSenderRequestDTO {

    private String userEmail;
    private String taskName;
    private String description;
    private LocalDateTime schedulingDate;
    private TaskStatusEnum status;
}
