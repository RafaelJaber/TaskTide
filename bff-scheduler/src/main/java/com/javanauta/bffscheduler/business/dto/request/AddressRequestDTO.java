package com.javanauta.bffscheduler.business.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequestDTO {

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
