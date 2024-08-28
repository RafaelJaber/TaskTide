package com.javanauta.user.business.dto.request;

import com.javanauta.user.infrastructure.enums.PhoneType;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactRequestDTO {

    private Integer areaCode;
    private Integer phoneNumber;
    private PhoneType type;
    private Boolean isWhatsapp;
}
