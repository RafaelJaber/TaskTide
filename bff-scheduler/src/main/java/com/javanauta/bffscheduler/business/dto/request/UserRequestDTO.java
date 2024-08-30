package com.javanauta.bffscheduler.business.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

    @Schema(description = "The user's full name.", example = "John Doe")
    private String name;

    @Schema(description = "The user's email address.", example = "john.doe@example.com")
    private String email;

    @Schema(description = "The user's password.", example = "P@ssw0rd")
    private String password;

    @Schema(description = "List of addresses associated with the user.")
    private List<AddressRequestDTO> addresses;

    @Schema(description = "List of contacts associated with the user.")
    private List<ContactRequestDTO> contacts;
}
