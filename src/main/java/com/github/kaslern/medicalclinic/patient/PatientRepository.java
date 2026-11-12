package com.github.kaslern.medicalclinic.patient;

import java.util.List;
import java.util.Optional;

public interface PatientRepository {

    Optional<Patient> findByEmail(String email);
    List<Patient> findAll();
    Patient save(Patient patient);
    void deleteByEmail(String email);
}
