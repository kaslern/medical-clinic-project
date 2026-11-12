package com.github.kaslern.medicalclinic.patient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> findByEmail(String email) {
        return patientRepository.findByEmail(email);
    }

    public Optional<Patient> update(String email, Patient changes) {
        return patientRepository.findByEmail(email).map(existing -> {
            existing.setFirstName(changes.getFirstName());
            existing.setLastName(changes.getLastName());
            existing.setPhoneNumber(changes.getPhoneNumber());
            existing.setBirthday(changes.getBirthday());
            return patientRepository.save(existing);
        });
    }

    public void deleteByEmail(String email)  {
        patientRepository.deleteByEmail(email);
    }

}
