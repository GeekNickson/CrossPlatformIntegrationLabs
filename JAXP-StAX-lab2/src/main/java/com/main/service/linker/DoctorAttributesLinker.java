package com.main.service.linker;

import com.main.model.Doctor;
import com.main.storage.DoctorAttributesContainer;

import java.util.List;


public class DoctorAttributesLinker {
    private final DoctorAttributesContainer containerSnapshot;

    public DoctorAttributesLinker() {
        this.containerSnapshot = DoctorAttributesContainer.getInstance();
    }

    public void link(Doctor doctor) {
        containerSnapshot.getSpecialty(doctor.getId())
                .ifPresent(doctor::setSpecialty);
        doctor.setServices(containerSnapshot.getServices(doctor.getId()));
    }

    public void linkAll(List<Doctor> doctors) {
        doctors.forEach(this::link);
    }
}
