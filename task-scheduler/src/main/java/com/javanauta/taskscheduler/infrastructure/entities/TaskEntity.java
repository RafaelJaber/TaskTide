package com.javanauta.taskscheduler.infrastructure.entities;

import com.javanauta.taskscheduler.infrastructure.enums.TaskStatusEnum;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "task")
public class TaskEntity {

    @Id
    private String id;

    @Field("task_name")
    private String taskName;

    @Field("description")
    private String description;

    @Field("scheduling_date")
    private LocalDateTime schedulingDate;

    @Field("user_email")
    private String userEmail;

    @Field("status")
    private TaskStatusEnum status;

    @CreatedDate
    @Field("created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Field("updated_at")
    private LocalDateTime updatedAt;
}
