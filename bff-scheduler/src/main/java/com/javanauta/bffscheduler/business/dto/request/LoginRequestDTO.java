package com.javanauta.bffscheduler.business.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO {

    @Schema(description = "The user's email address.", example = "user@example.com")
    private String email;

    @Schema(description = "The user's password.", example = "P@ssw0rd")
    private String password;
}
