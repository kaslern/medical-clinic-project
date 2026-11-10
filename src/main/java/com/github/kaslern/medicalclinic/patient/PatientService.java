package com.github.kaslern.medicalclinic.patient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public void addPatient(Patient patient) {
        if (patientRepository.findByEmail(patient.getEmail()).isPresent()) {
            throw new PatientAlreadyExistException(patient.getEmail());
        }
        patientRepository.save(patient);
    }

    public int countPatients() {
        return patientRepository.findAll().size();
    }

}
