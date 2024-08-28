package com.javanauta.user.infrastructure.entities;

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
@Table(name = "TB_Address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "street")
    private String street;

    @Column(name = "number")
    private String number;

    @Column(name = "complement")
    private String complement;

    @Column(nullable = false, name = "neighborhood")
    private String neighborhood;

    @Column(nullable = false, name = "city")
    private String city;

    @Column(nullable = false, name = "state", length = 2)
    private String state;

    @Column(name = "zip_code")
    private String zipCode;
}
