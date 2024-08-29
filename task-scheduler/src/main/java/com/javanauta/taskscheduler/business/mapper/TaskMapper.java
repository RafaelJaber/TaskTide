package com.javanauta.taskscheduler.business.mapper;

import com.javanauta.taskscheduler.business.dto.request.TaskRequestDTO;
import com.javanauta.taskscheduler.business.dto.response.TaskResponseDTO;
import com.javanauta.taskscheduler.infrastructure.entities.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "userEmail", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    TaskEntity toTaskEntity(TaskRequestDTO dto);

    TaskResponseDTO toTaskResponseDTO(TaskEntity entity);
}
