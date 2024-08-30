package com.javanauta.taskscheduler.infrastructure.client;

import com.javanauta.taskscheduler.business.dto.response.UserResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user-microservice")
public interface UserClient {

    @GetMapping("/users/{email}")
    UserResponseDTO findUserByEmail(@PathVariable String email,
                                    @RequestHeader("Authorization") String token);
}
