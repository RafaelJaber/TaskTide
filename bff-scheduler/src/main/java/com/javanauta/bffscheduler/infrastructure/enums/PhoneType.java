package com.javanauta.bffscheduler.infrastructure.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Represents the type of phone number associated with a contact.")
public enum PhoneType {
    @Schema(description = "Represents a home phone number.")
    HOME,

    @Schema(description = "Represents an office or work phone number.")
    OFFICE,

    @Schema(description = "Represents a mobile or cell phone number.")
    MOBILE,

    @Schema(description = "Represents a fax number.")
    FAX,

    @Schema(description = "Represents a phone number that doesn't fit into the other categories.")
    OTHER,

    @Schema(description = "Represents an emergency contact number.")
    EMERGENCY,

    @Schema(description = "Represents a VOIP (Voice over IP) phone number.")
    VOIP,

    @Schema(description = "Represents a pager number.")
    PAGER
}
