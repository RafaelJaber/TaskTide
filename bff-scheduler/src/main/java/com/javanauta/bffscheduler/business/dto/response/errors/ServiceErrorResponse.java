package com.javanauta.bffscheduler.business.dto.response.errors;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceErrorResponse {
    @JsonProperty("timestamp")
    private String timestamp;

    @JsonProperty("httpStatus")
    private int httpStatus;

    @JsonProperty("httpError")
    private String httpError;

    @JsonProperty("error")
    private String error;

    @JsonProperty("path")
    private String path;
}
