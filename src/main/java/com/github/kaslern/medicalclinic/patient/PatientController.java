package com.github.kaslern.medicalclinic.patient;

import com.github.kaslern.medicalclinic.patient.dto.PasswordChangeCommand;
import com.github.kaslern.medicalclinic.patient.dto.PatientCreateCommand;
import com.github.kaslern.medicalclinic.patient.dto.PatientUpdateCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.github.kaslern.medicalclinic.patient.dto.PatientDto;

import java.net.URI;
import java.util.List;

@Tag(name = "Patients", description = "Patient catalog: add, remove, browse or change patients")
@Slf4j
@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @Operation(summary = "List all patients")
    @GetMapping
    public List<PatientDto> findAll() {
        return patientService.getAllPatients();
    }

    @Operation(summary = "Find patient by email")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Patient found"),
            @ApiResponse(responseCode = "404", description = "No patient with this email",
            content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    })
    @GetMapping(params = "email")
    public PatientDto findByEmail(@RequestParam String email) {
        return patientService.findByEmail(email);
    }

    @Operation(summary = "Add a patient")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Patient added"),
            @ApiResponse(responseCode = "400", description = "Invalid fields (Null, empty field etc.)",
                    content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "409", description = "Patient already exist",
                    content = @Content(schema = @Schema(implementation = ProblemDetail.class)))

    })
    @PostMapping
    public ResponseEntity<PatientDto> add(@Valid @RequestBody PatientCreateCommand command) {
        log.info("Creating patient: {}", command);
        PatientDto saved = patientService.addPatient(command);
        return ResponseEntity.created(URI.create("/patients/" + command.email())).body(saved);
    }

    @Operation(summary = "Change patient data")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Patient data changed"),
            @ApiResponse(responseCode = "400", description = "Invalid fields (Null, empty field etc.)",
                    content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "No patient with this email",
                    content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    })
    @PutMapping("/{email}")
    public PatientDto update(@PathVariable String email, @Valid @RequestBody PatientUpdateCommand command) {
        return patientService.update(email, command);
    }

    @Operation(summary = "Change patient password")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Password changed"),
            @ApiResponse(responseCode = "400", description = "Password too short, empty or null",
                    content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "No patient with this email",
                    content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    })
    @PatchMapping("/{email}/password")
    public PatientDto changePassword(@PathVariable String email,
                                                     @Valid @RequestBody PasswordChangeCommand command) {
        log.info("Request: {}", command);
        return patientService.changePassword(email, command);

    }

    @Operation(summary = "Remove patient")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Patient removed or never existed"),
    })
    @DeleteMapping("/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByEmail(@PathVariable String email) {
        patientService.deleteByEmail(email);
    }
}
