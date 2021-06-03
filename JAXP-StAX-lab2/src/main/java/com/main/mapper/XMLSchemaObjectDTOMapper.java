package com.main.mapper;

import com.main.dto.XMLSchemaObjectDTO;
import com.main.model.Doctor;
import com.main.model.Patient;
import com.main.model.Service;
import com.main.model.Specialty;

public class XMLSchemaObjectDTOMapper {

    public static Doctor toDoctor(XMLSchemaObjectDTO object) {
        Doctor doctor = new Doctor();
        doctor.setId(object.getId());
        doctor.setFirstName(object.getFirstName());
        doctor.setLastName(object.getLastName());
        doctor.setAge(object.getAge());
        doctor.setCategory(object.getCategory());
        doctor.setExperience(object.getExperience());
        doctor.setVacationStart(object.getVacationStart());
        doctor.setVacationEnd(object.getVacationEnd());
        return doctor;
    }

    public static Patient toPatient(XMLSchemaObjectDTO object) {
        Patient patient = new Patient();
        patient.setId(object.getId());
        patient.setFirstName(object.getFirstName());
        patient.setLastName(object.getLastName());
        patient.setAge(object.getAge());
        patient.setInsured(object.getInsured());
        return patient;
    }

    public static Specialty toSpecialty(XMLSchemaObjectDTO object) {
        Specialty specialty = new Specialty();
        specialty.setId(object.getId());
        specialty.setName(object.getName());
        return specialty;
    }

    public static Service toService(XMLSchemaObjectDTO object) {
        Service service = new Service();
        service.setId(object.getId());
        service.setName(object.getName());
        service.setPrice(object.getPrice());
        return service;
    }
}
