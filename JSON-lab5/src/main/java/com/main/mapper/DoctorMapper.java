package com.main.mapper;

import com.main.model.common.Doctor;
import com.main.service.dto.DoctorDto;
import com.main.service.dto.ServiceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DoctorMapper {
    private final SpecialtyMapper specialtyMapper;
    private final ServiceMapper serviceMapper;

    public DoctorDto toDoctorDto(Doctor doctor) {
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setId(doctor.getId());
        doctorDto.setFirstName(doctor.getFirstName());
        doctorDto.setLastName(doctor.getLastName());
        doctorDto.setAge(doctor.getAge());
        doctorDto.setCategory(doctor.getCategory());
        doctorDto.setExperience(doctor.getExperience());
        doctorDto.setSpecialty(specialtyMapper.toSpecialtyDto(doctor.getSpecialty()));

        List<ServiceDto> servicesDto = new ArrayList<>();

        doctor.getServices()
                .stream()
                .map(serviceMapper::toServiceDto)
                .forEach(servicesDto::add);

        doctorDto.setServices(servicesDto);
        doctorDto.setVacationStart(doctor.getVacationStart());
        doctorDto.setVacationEnd(doctor.getVacationEnd());
        doctorDto.setOnVacation(doctor.onVacation());
        return doctorDto;
    }
}
