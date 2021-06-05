package com.main.service.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DoctorDto implements Serializable {
    private String id;
    private String firstName;
    private String lastName;
    private Short age;
    private String category;
    private Short experience;
    private LocalDate vacationStart;
    private LocalDate vacationEnd;
    private Boolean onVacation;
    private SpecialtyDto specialty;
    private List<ServiceDto> services;
}
