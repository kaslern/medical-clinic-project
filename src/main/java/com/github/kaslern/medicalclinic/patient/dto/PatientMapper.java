package com.github.kaslern.medicalclinic.patient.dto;

import com.github.kaslern.medicalclinic.patient.Patient;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PatientMapper {
    PatientDto toDto(Patient patient);
    Patient toEntity(PatientCreateCommand command);

    @Mapping(target = "email", ignore = true)
    @Mapping(target = "idCardNo", ignore = true)
    void update(PatientUpdateCommand command, @MappingTarget Patient patient);
}

