package com.main.service.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.List;


@RequiredArgsConstructor
@Getter
public class DoctorsDto implements Serializable {
    private final List<DoctorDto> doctors;
}
