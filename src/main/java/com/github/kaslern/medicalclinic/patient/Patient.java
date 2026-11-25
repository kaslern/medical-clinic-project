package com.github.kaslern.medicalclinic.patient;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "patients")
@ToString(exclude = {"password", "idCardNo"})

public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Setter
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    @Setter
    private String idCardNo;
    @Column(nullable = false)
    @Setter
    private String firstName;
    @Column(nullable = false)
    @Setter
    private String lastName;
    @Column(nullable = false)
    @Setter
    private String phoneNumber;
    @Column(nullable = false)
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
