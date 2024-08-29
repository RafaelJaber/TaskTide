package com.javanauta.taskscheduler.business.dto.response;

import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private String email;
    private String password;

}
