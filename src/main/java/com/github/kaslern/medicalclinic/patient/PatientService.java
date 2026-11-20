package com.github.kaslern.medicalclinic.patient;

import com.github.kaslern.medicalclinic.exception.MedicalClinicError;
import com.github.kaslern.medicalclinic.exception.MedicalClinicException;
import com.github.kaslern.medicalclinic.patient.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public PatientDto addPatient(PatientCreateCommand command) {
        if (patientRepository.findByEmail(command.email()).isPresent()) {
            throw new MedicalClinicException(MedicalClinicError.PATIENT_ALREADY_EXIST, "Patient already exist");
        }
        Patient patient = patientMapper.toEntity(command);
        return patientMapper.toDto(patientRepository.save(patient));
    }

    public int countPatients() {
        return patientRepository.findAll().size();
    }

    public List<PatientDto> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(patientMapper::toDto)
                .toList();
    }

    public PatientDto findByEmail(String email) {
        return patientRepository.findByEmail(email)
                .map(patientMapper::toDto)
                .orElseThrow(() -> new MedicalClinicException(MedicalClinicError.PATIENT_NOT_FOUND,
                        "patient with email " + email + " not found"));
    }

    public Optional<PatientDto> update(String email, PatientUpdateCommand command) {
        return patientRepository.findByEmail(email)
                .map(patient -> { patientMapper.update(command, patient);
            return patientMapper.toDto(patientRepository.save(patient));
        });
    }

    public Optional<PatientDto> changePassword(String email, PasswordChangeCommand command) {
        return patientRepository.findByEmail(email).map( existing -> {
            existing.changePassword(command.password());
            return patientMapper.toDto(patientRepository.save(existing));
        });
    }

    public void deleteByEmail(String email)  {
        patientRepository.deleteByEmail(email);
    }



}
