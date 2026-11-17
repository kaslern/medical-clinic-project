package com.github.kaslern.medicalclinic.patient.dto;

public record PasswordChangeCommand(String password) {
    @Override
    public String toString() {
        return "PasswordChangeCommand[password=***]";
    }
}
