package com.main.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class Person {
    protected String id;
    protected String firstName;
    protected String lastName;
    protected Short age;
}
