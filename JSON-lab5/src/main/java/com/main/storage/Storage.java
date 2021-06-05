package com.main.storage;

import com.main.model.common.Doctor;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Storage {
    @Getter
    private final List<Doctor> doctors;

    private Storage() {
        this.doctors = new ArrayList<>();
    }

    public void clear() {
        doctors.clear();
    }

    public void save(Doctor doctor) {
        doctors.add(doctor);
    }

    public void save(List<Doctor> doctors) {
        this.doctors.addAll(doctors);
    }

    public List<Doctor> findAll() {
        return doctors;
    }
}
