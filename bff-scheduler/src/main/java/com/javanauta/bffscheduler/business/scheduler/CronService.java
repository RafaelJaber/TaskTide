package com.javanauta.bffscheduler.business.scheduler;

import com.javanauta.bffscheduler.business.dto.request.EmailSenderRequestDTO;
import com.javanauta.bffscheduler.business.dto.request.TaskStatusRequestDTO;
import com.javanauta.bffscheduler.business.dto.response.TaskResponseDTO;
import com.javanauta.bffscheduler.business.usecases.notificationmicroservices.EmailSenderService;
import com.javanauta.bffscheduler.business.usecases.taskscheduling.ChangeTaskStatusService;
import com.javanauta.bffscheduler.business.usecases.taskscheduling.FindPendingTasksBySchedulingDateService;
import com.javanauta.bffscheduler.core.utils.UserUtil;
import com.javanauta.bffscheduler.infrastructure.enums.TaskStatusEnum;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CronService {
    private final Logger LOG = LoggerFactory.getLogger(CronService.class);
    private final FindPendingTasksBySchedulingDateService findPendingTasksBySchedulingDateService;
    private final ChangeTaskStatusService changeTaskStatusService;
    private final EmailSenderService emailSenderService;
    private final UserUtil userUtil;

    @Scheduled(cron = "${task-tide.cron-params.send-notification-tasks}")
    public void FindTasksNextHour() {
        try {
            String token = userUtil.getSystemUserToken();
            LocalDateTime futureHour = LocalDateTime.now().plusHours(1);
            LocalDateTime futureHourPlusTen = LocalDateTime.now().plusHours(1).plusMinutes(10);
            List<TaskResponseDTO> taskList = this.findPendingTasksBySchedulingDateService
                    .execute(futureHour, futureHourPlusTen,  token);
            TaskStatusRequestDTO taskStatusRequestDTO = new TaskStatusRequestDTO(TaskStatusEnum.NOTIFIED);
            taskList.forEach(task -> {
                LOG.info("Sending notification email for task {}", task.getId());
                emailSenderService.execute(getEmailSenderService(task));
                changeTaskStatusService.execute(taskStatusRequestDTO, task.getId(), token);
            });
        } catch (RuntimeException ex) {
            LOG.error("CRON ERROR ========================");
            LOG.error(ex.getMessage(), ex);
            LOG.error("CRON ERROR ========================");
        }

    }

    private EmailSenderRequestDTO getEmailSenderService(TaskResponseDTO dto) {
        return EmailSenderRequestDTO.builder()
                .userEmail(dto.getUserEmail())
                .taskName(dto.getTaskName())
                .description(dto.getDescription())
                .schedulingDate(dto.getSchedulingDate())
                .status(dto.getStatus())
                .build();
    }

}
