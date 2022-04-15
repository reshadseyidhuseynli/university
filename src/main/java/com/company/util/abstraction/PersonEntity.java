package com.company.util.abstraction;

import javax.persistence.Column;
import java.time.LocalDate;

public abstract class PersonEntity extends IdentifierEntity {
    @Column(name = "surname")
    private String surname;
    @Column(name = "birthdate")
    private LocalDate birthdate;

    public PersonEntity() {
    }
    protected PersonEntity(Integer id, String name, String surname, LocalDate birthdate) {
        super(id, name);
        this.surname = surname;
        this.birthdate = birthdate;
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
