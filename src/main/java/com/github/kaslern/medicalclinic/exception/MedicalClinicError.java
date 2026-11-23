package com.github.kaslern.medicalclinic.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MedicalClinicError {
    PATIENT_ALREADY_EXIST(HttpStatus.CONFLICT),
    PATIENT_NOT_FOUND(HttpStatus.NOT_FOUND);

    private final HttpStatus status;
}
