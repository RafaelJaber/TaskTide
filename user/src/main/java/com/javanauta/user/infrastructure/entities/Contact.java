package com.javanauta.user.infrastructure.entities;

import com.javanauta.user.infrastructure.enums.PhoneType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_Contact")
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
