package com.github.kaslern.medicalclinic.patient;

import com.github.kaslern.medicalclinic.patient.dto.PasswordChangeCommand;
import com.github.kaslern.medicalclinic.patient.dto.PatientCreateCommand;
import com.github.kaslern.medicalclinic.patient.dto.PatientDto;
import com.github.kaslern.medicalclinic.patient.dto.PatientUpdateCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientDto addPatient(PatientCreateCommand command) {
        if (patientRepository.findByEmail(command.email()).isPresent()) {
            throw new PatientAlreadyExistException(command.email());
        }
        Patient patient = Patient.builder()
                .email(command.email())
                .password(command.password())
                .idCardNo(command.idCardNo())
                .firstName(command.firstName())
                .lastName(command.lastName())
                .phoneNumber(command.phoneNumber())
                .birthday(command.birthday())
                .build();

       return toDto(patientRepository.save(patient));
    }

    public int countPatients() {
        return patientRepository.findAll().size();
    }

    public List<PatientDto> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    public Optional<PatientDto> findByEmail(String email) {
        return patientRepository.findByEmail(email).map(this::toDto);
    }

    public Optional<PatientDto> update(String email, PatientUpdateCommand command) {
        return patientRepository.findByEmail(email).map(existing -> {
            existing.setFirstName(command.firstName());
            existing.setLastName(command.lastName());
            existing.setPhoneNumber(command.phoneNumber());
            existing.setBirthday(command.birthday());
            return toDto(patientRepository.save(existing));
        });
    }

    public Optional<PatientDto> changePassword(String email, PasswordChangeCommand command) {
        return patientRepository.findByEmail(email).map( existing -> {
            existing.changePassword(command.password());
            return toDto(patientRepository.save(existing));
        });
    }

    public void deleteByEmail(String email)  {
        patientRepository.deleteByEmail(email);
    }

    private PatientDto toDto(Patient patient) {
        return new PatientDto(patient.getEmail(), patient.getFirstName(), patient.getLastName(),
                patient.getPhoneNumber(), patient.getBirthday());
    }

}
