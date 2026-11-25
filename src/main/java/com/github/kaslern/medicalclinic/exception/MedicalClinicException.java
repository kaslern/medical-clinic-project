package com.github.kaslern.medicalclinic.exception;

import lombok.Getter;

@Getter
public class MedicalClinicException extends RuntimeException {
   private final MedicalClinicError error;

    public MedicalClinicException(MedicalClinicError error, String message) {
        super(message);
        this.error = error;
    }
}

