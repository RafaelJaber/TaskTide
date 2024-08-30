package com.javanauta.bffscheduler.business.dto.request;

import com.javanauta.bffscheduler.infrastructure.enums.PhoneType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactRequestDTO {

    @Schema(description = "The area code of the contact number.", example = "212")
    private Integer areaCode;

    @Schema(description = "The contact's phone number.", example = "5551234")
    private Integer phoneNumber;

    @Schema(description = "The type of phone number (e.g., MOBILE, LANDLINE).", example = "MOBILE")
    private PhoneType type;

    @Schema(description = "Indicates whether the number is associated with WhatsApp.", example = "true")
    private Boolean isWhatsapp;
}
