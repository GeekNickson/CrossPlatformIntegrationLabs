package com.main.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class XMLSchemaObjectDTO {
    private String id;
    private String firstName;
    private String lastName;
    private Short age;
    private String category;
    private Short experience;
    private LocalDate vacationStart;
    private LocalDate vacationEnd;
    private String name;
    private Double price;
    private Boolean insured;
    private String doctorId;
}
