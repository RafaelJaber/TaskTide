package com.javanauta.bffscheduler.core.utils;

import com.javanauta.bffscheduler.business.dto.request.LoginRequestDTO;
import com.javanauta.bffscheduler.business.dto.response.LoginResponseDTO;
import com.javanauta.bffscheduler.business.usecases.usermicroservices.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUtil {

    private final LoginService loginService;

    @Value("${task-tide.system-user.email}")
    private String systemUserEmail;
    @Value("${task-tide.system-user.password}")
    private String systemUserPassword;

    public String getSystemUserToken() {
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setEmail(systemUserEmail);
        loginRequestDTO.setPassword(systemUserPassword);
        LoginResponseDTO result = loginService.execute(loginRequestDTO);
        return "Bearer " + result.getAccessToken();
    }
}
