package com.github.kaslern.medicalclinic.patient;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Patient {

    private String email;

    @ToString.Exclude
    private String password;

    @ToString.Exclude
    private String idCardNo;

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDate birthday;
}
