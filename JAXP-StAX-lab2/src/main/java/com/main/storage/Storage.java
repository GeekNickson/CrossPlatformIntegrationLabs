package com.main.storage;

import com.main.constants.TagConstants;
import com.main.dto.XMLSchemaObjectDTO;
import com.main.mapper.XMLSchemaObjectDTOMapper;
import com.main.model.Doctor;
import com.main.model.Patient;
import com.main.model.Service;
import com.main.model.Specialty;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Storage {
    private static final Storage storage = new Storage();

    @Getter
    private final List<Doctor> doctors;

    @Getter
    private final List<Patient> patients;

    @Getter
    private final List<Specialty> specialties;

    @Getter
    private final List<Service> services;

    private Storage() {
        this.doctors = new ArrayList<>();
        this.patients = new ArrayList<>();
        this.specialties = new ArrayList<>();
        this.services = new ArrayList<>();
    }

    public static Storage getInstance() {
        return storage;
    }

    public void save(XMLSchemaObjectDTO object, String tagName) {
        switch (tagName) {
            case TagConstants.DOCTOR:
                doctors.add(XMLSchemaObjectDTOMapper.toDoctor(object));
                break;
            case TagConstants.PATIENT:
                patients.add(XMLSchemaObjectDTOMapper.toPatient(object));
                break;
            case TagConstants.SERVICE:
                Service service = XMLSchemaObjectDTOMapper.toService(object);
                services.add(service);
                List<Service> storedServices = DoctorAttributesContainer.getInstance()
                        .getDoctorServicesLink().getOrDefault(object.getDoctorId(), new ArrayList<>());
                storedServices.add(service);
                DoctorAttributesContainer.getInstance()
                        .getDoctorServicesLink().put(object.getDoctorId(), storedServices);
                break;
            case TagConstants.SPECIALTY:
                Specialty specialty = XMLSchemaObjectDTOMapper.toSpecialty(object);
                specialties.add(specialty);
                DoctorAttributesContainer.getInstance().getDoctorSpecialtiesLink().put(object.getDoctorId(), specialty);
                break;
            default:
                throw new IllegalArgumentException("Invalid tag name");
        }
    }
}
