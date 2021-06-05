package com.main.storage;

import com.main.model.common.Doctor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static final Storage storage = new Storage();

    @Getter
    private final List<Doctor> doctors;

    private Storage() {
        this.doctors = new ArrayList<>();
    }

    public static Storage getInstance() {
        return storage;
    }
}
