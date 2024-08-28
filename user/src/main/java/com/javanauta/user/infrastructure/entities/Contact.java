package com.javanauta.user.infrastructure.entities;

import com.javanauta.user.infrastructure.enums.PhoneType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer areaCode;

    @Column(nullable = false)
    private Integer phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "type")
    private PhoneType type;

    @Column(name = "is_whatsapp")
    private Boolean isWhatsapp;
}
