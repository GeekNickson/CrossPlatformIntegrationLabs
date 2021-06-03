package com.main.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Doctor extends Person {
    private String category;
    private Short experience;
    private LocalDate vacationStart;
    private LocalDate vacationEnd;
    private Specialty specialty;
    private List<Service> services;

    public boolean onVacation() {
        LocalDate currentDate = LocalDate.now();
        return vacationStart.isBefore(currentDate) && vacationEnd.isAfter(currentDate);
    }
}
