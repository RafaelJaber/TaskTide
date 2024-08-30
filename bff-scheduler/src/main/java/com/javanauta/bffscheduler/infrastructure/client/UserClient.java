package com.javanauta.bffscheduler.infrastructure.client;

import com.javanauta.bffscheduler.business.dto.request.LoginRequestDTO;
import com.javanauta.bffscheduler.business.dto.request.UserRequestDTO;
import com.javanauta.bffscheduler.business.dto.response.LoginResponseDTO;
import com.javanauta.bffscheduler.business.dto.response.UserResponseDTO;
import com.javanauta.bffscheduler.core.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-microservice", configuration = FeignConfig.class)
public interface UserClient {

    @GetMapping("users/{email}")
    UserResponseDTO findUserByEmail(@PathVariable("email") String email);

    @PostMapping("users/login")
    LoginResponseDTO login(@RequestBody LoginRequestDTO dto);

    @PostMapping("/users")
    UserResponseDTO insert(@RequestBody UserRequestDTO request);

    @PutMapping("/users/me")
    UserResponseDTO updateCurrentUserData(@RequestBody UserRequestDTO request,
                                          @RequestHeader("Authorization") String token);

    @DeleteMapping("/users/{email}")
    Void deleteUser(@PathVariable("email") String email,
                    @RequestHeader("Authorization") String token);
}
