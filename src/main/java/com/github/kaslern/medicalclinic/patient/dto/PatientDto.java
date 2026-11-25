package com.github.kaslern.medicalclinic.patient.dto;

import java.time.LocalDate;

public record PatientDto(String email, String firstName, String lastName, String phoneNumber, LocalDate birthday) {
}
