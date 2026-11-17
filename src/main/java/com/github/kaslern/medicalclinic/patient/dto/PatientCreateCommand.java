package com.github.kaslern.medicalclinic.patient.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record PatientCreateCommand(

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "Password can not be empty")
        @Size(min = 6)
        String password,

        @NotBlank(message = "IdCardNo is required")
        String idCardNo,

        @NotBlank(message = "First name is required")
        String firstName,

        @NotBlank(message = "Last name is required")
        String lastName,

        @NotBlank
        @Size(min = 9, max = 15, message = "Phone number must contain 9 to 15 characters")
        String phoneNumber,

        @NotNull(message = "Birthday is required")
        @PastOrPresent(message = "Birthday cannot be in the future")
        LocalDate birthday) {
    @Override
    public String toString() {
        return "PatientCreateCommand[email=" + email
                + ", password=***"
                + ", firstName=" + firstName + "]";
    }
}
