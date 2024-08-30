package com.javanauta.bffscheduler.business.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponseDTO {
    @Schema(description = "The unique identifier of the address.", example = "1")
    private Long id;

    @Schema(description = "The street name of the address.", example = "Main Street")
    private String street;

    @Schema(description = "The number of the address.", example = "123")
    private String number;

    @Schema(description = "Additional information about the address.", example = "Apartment 4B")
    private String complement;

    @Schema(description = "The neighborhood of the address.", example = "Downtown")
    private String neighborhood;

    @Schema(description = "The city of the address.", example = "New York")
    private String city;

    @Schema(description = "The state of the address.", example = "NY")
    private String state;

    @Schema(description = "The postal code of the address.", example = "10001")
    private String zipCode;
}
