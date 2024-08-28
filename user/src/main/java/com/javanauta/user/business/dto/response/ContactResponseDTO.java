package com.javanauta.user.business.dto.response;

import com.javanauta.user.infrastructure.enums.PhoneType;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactResponseDTO {
    private Long id;
    private Integer areaCode;
    private Integer phoneNumber;
    private PhoneType type;
    private Boolean isWhatsapp;
}
