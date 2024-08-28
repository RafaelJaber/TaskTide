package com.javanauta.user.business.dto.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

    private String name;
    private String email;
    private String password;
    private List<AddressRequestDTO> addresses;
    private List<ContactRequestDTO> contacts;
}
