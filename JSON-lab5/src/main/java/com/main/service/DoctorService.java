package com.main.service;

import com.main.mapper.DoctorMapper;
import com.main.model.common.Doctor;
import com.main.service.dto.DoctorDto;
import com.main.storage.Storage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final Storage storage;
    private final DoctorMapper doctorMapper;

    public List<DoctorDto> save(List<Doctor> doctors) {
        storage.save(doctors);
        return findAll();
    }

    public void save(Doctor doctor) {
        storage.save(doctor);
    }

    public List<DoctorDto> findAll() {
        return storage.findAll()
                .stream()
                .map(doctorMapper::toDoctorDto)
                .collect(Collectors.toList());
    }

    public void deleteAll() {
        storage.clear();
    }
}
