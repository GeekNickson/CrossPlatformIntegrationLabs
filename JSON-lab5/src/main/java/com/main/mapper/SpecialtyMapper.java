package com.main.mapper;

import com.main.model.common.Specialty;
import com.main.service.dto.SpecialtyDto;
import org.springframework.stereotype.Component;

@Component
public class SpecialtyMapper {
    public SpecialtyDto toSpecialtyDto(Specialty specialty) {
        SpecialtyDto specialtyDto = new SpecialtyDto();
        specialtyDto.setId(specialty.getId());
        specialtyDto.setName(specialty.getName());
        return specialtyDto;
    }
}
