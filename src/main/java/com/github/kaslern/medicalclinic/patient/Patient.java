package com.github.kaslern.medicalclinic.patient;

import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@ToString

public class Patient {

    @Setter
    private String email;

    @ToString.Exclude
    private String password;
    @Setter
    @ToString.Exclude
    private String idCardNo;
    @Setter
    private String firstName;
    @Setter
    private String lastName;
    @Setter
    private String phoneNumber;
    @Setter
    private LocalDate birthday;

    @Builder
    public Patient(String email, String password, String idCardNo, String firstName, String lastName,
                   String phoneNumber, LocalDate birthday) {
        validatePassword(password);
        this.email = email;
        this.password = password;
        this.idCardNo = idCardNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
    }

    public void changePassword(String newPassword) {
        validatePassword(newPassword);
        this.password = newPassword;
    }

    private static void validatePassword(String password) {
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password can not be null or empty");
        }
        if (password.length() < 6) {
            throw new IllegalArgumentException("Password must have at least 6 characters");
        }
    }
}
