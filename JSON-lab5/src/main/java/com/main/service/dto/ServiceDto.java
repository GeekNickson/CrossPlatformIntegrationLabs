package com.main.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ServiceDto implements Serializable {
    private String id;
    private String name;
    private Double price;
}
