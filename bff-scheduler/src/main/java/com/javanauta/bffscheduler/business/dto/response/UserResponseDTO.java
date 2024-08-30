package com.javanauta.bffscheduler.business.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    @Schema(description = "The user's full name.", example = "John Doe")
    private String name;

    @Schema(description = "The user's email address.", example = "john.doe@example.com")
    private String email;

    @Schema(description = "List of addresses associated with the user.")
    private List<AddressResponseDTO> addresses;

    @Schema(description = "List of contacts associated with the user.")
    private List<ContactResponseDTO> contacts;

}
