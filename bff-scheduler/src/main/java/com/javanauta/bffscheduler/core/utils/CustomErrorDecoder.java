package com.javanauta.bffscheduler.core.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javanauta.bffscheduler.business.dto.response.errors.ServiceErrorResponse;
import com.javanauta.bffscheduler.infrastructure.exception.FeignGeneralException;
import com.javanauta.bffscheduler.infrastructure.exception.FeignMicroservicesClientException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class CustomErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Exception decode(String methodKey, Response response) {
        ServiceErrorResponse errorResponse;

        if (response.body() != null) {
            try {
                errorResponse = objectMapper.readValue(response.body().asInputStream(), ServiceErrorResponse.class);
                return new FeignMicroservicesClientException(
                        OffsetDateTime.parse(errorResponse.getTimestamp()),
                        errorResponse.getHttpStatus(),
                        errorResponse.getHttpError(),
                        errorResponse.getError(),
                        errorResponse.getPath()
                );
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                return new FeignGeneralException(ex.getMessage(), ex.getCause());
            }
        } else {
            return new FeignGeneralException("Status code: " + response.status());
        }
    }
}
