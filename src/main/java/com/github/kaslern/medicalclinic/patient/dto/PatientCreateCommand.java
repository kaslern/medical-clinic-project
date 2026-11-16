package com.github.kaslern.medicalclinic.patient.dto;

import java.time.LocalDate;

public record PatientCreateCommand(String email, String password, String idCardNo, String firstName,
                                   String lastName, String phoneNumber, LocalDate birthday) {
    @Override
    public String toString() {
        return "PatientCreateCommand[email=" + email
                + ", password=***"
                + ", firstName=" + firstName + "]";
    }
}
