package com.company.abstraction;

import javax.persistence.Column;
import java.time.LocalDate;

public abstract class Person extends Identifier {
    @Column(name = "surname")
    private String surname;
    @Column(name = "birthdate")
    private LocalDate birthdate;

    public Person() {
    }
    protected Person(int id, String name, String surname, LocalDate birthdate) {
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
