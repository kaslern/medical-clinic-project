package com.github.kaslern.medicalclinic.patient.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record PatientUpdateCommand(

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
}
