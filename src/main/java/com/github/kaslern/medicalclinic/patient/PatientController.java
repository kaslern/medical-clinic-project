package com.github.kaslern.medicalclinic.patient;

import com.github.kaslern.medicalclinic.patient.dto.PasswordChangeCommand;
import com.github.kaslern.medicalclinic.patient.dto.PatientCreateCommand;
import com.github.kaslern.medicalclinic.patient.dto.PatientUpdateCommand;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.github.kaslern.medicalclinic.patient.dto.PatientDto;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public List<PatientDto> findAll() {
        return patientService.getAllPatients();
    }

    @GetMapping(params = "email")
    public PatientDto findByEmail(@RequestParam String email) {
        return patientService.findByEmail(email);
    }

    @PostMapping
    public ResponseEntity<PatientDto> add(@Valid @RequestBody PatientCreateCommand command) {
        log.info("Creating patient: {}", command);
        PatientDto saved = patientService.addPatient(command);
        return ResponseEntity.created(URI.create("/patients/" + command.email())).body(saved);
    }

    @PutMapping("/{email}")
    public ResponseEntity<PatientDto> update(@PathVariable String email, @Valid @RequestBody PatientUpdateCommand command) {
        return patientService.update(email, command)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{email}/password")
    public ResponseEntity<PatientDto> changePassword(@PathVariable String email,
                                                     @Valid @RequestBody PasswordChangeCommand command) {
        log.info("Request: {}", command);
        return patientService.changePassword(email, command)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByEmail(@PathVariable String email) {
        patientService.deleteByEmail(email);
    }
}
