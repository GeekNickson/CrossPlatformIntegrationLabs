package com.main.storage;

import com.main.model.Service;
import com.main.model.Specialty;
import lombok.Getter;

import java.util.*;

public class DoctorAttributesContainer {
    private static final DoctorAttributesContainer container = new DoctorAttributesContainer();

    @Getter
    private final Map<String, Specialty> doctorSpecialtiesLink;

    @Getter
    private final Map<String, List<Service>> doctorServicesLink;

    private DoctorAttributesContainer() {
        this.doctorSpecialtiesLink = new HashMap<>();
        this.doctorServicesLink = new HashMap<>();
    }

    public static DoctorAttributesContainer getInstance() {
        return container;
    }

    public Optional<Specialty> getSpecialty(String doctorId) {
        return Optional.ofNullable(doctorSpecialtiesLink.get(doctorId));
    }

    public List<Service> getServices(String doctorId) {
        return  doctorServicesLink.getOrDefault(doctorId, new ArrayList<>());
    }
}
