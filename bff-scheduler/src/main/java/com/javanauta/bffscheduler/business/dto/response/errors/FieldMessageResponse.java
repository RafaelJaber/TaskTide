package com.javanauta.bffscheduler.business.dto.response.errors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FieldMessageResponse {

    private String fieldName;
    private String message;

}
