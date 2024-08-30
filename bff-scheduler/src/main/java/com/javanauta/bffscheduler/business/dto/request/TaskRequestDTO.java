package com.javanauta.bffscheduler.business.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequestDTO {

    private String taskName;
    private String description;
    private LocalDateTime schedulingDate;
}
