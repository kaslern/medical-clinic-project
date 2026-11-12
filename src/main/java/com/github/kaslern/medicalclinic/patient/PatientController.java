package com.github.kaslern.medicalclinic.patient;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public List<Patient> findAll() {
        return patientService.getAllPatients();
    }

    @GetMapping(params = "email")
    public ResponseEntity<Patient> findByEmail(@RequestParam String email) {
        return patientService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> addPatient(@RequestBody Patient patient) {
        patientService.addPatient(patient);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{email}")
    public ResponseEntity<Patient> update(@PathVariable String email, @RequestBody Patient patient) {
        return patientService.update(email, patient)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByEmail(@PathVariable String email) {
        patientService.deleteByEmail(email);
    }
}
