package com.javanauta.user.business.dto.response;

import lombok.*;

import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private String name;
    private String email;
    private List<AddressResponseDTO> addresses;
    private List<ContactResponseDTO> contacts;

}
