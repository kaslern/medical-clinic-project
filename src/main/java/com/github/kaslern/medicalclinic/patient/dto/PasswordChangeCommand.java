package com.github.kaslern.medicalclinic.patient.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PasswordChangeCommand(

        @NotBlank(message = "Password cannot be empty")
        @Size(min = 6, message = "Password must contain at least 6 characters")
        String password) {
    @Override
    public String toString() {
        return "PasswordChangeCommand[password=***]";
    }
}
