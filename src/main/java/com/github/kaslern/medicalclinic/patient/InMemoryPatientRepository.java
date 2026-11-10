package com.github.kaslern.medicalclinic.patient;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryPatientRepository implements PatientRepository {

    private final Map<String, Patient> patients = new HashMap<>();

    @Override
    public Optional<Patient> findByEmail(String email) {
        return Optional.ofNullable(patients.get(email));
    }

    @Override
    public void save(Patient patient) {
        patients.put(patient.getEmail(), patient);
    }

    @Override
    public List<Patient> findAll() {
        return List.copyOf(patients.values());
    }
}
