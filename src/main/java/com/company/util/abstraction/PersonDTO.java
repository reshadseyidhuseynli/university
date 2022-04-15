package com.company.util.abstraction;

import java.time.LocalDate;

public class PersonDTO extends IdentifierDTO{

    private String surname;
    private LocalDate birthdate;

    public PersonDTO() {
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
}
