package com.github.kaslern.medicalclinic.patient;

public class PatientAlreadyExistException extends RuntimeException {
    public PatientAlreadyExistException(String email) {
        super("Patient with email " + email + " already exist");
    }
}
