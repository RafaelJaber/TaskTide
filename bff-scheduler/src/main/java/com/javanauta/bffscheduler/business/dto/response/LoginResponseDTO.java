package com.javanauta.bffscheduler.business.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {

    @Schema(description = "The JWT access token issued after successful authentication.", example = "eyJhbGciOiJIUzI1NiIsInR5...")
    private String accessToken;
}
