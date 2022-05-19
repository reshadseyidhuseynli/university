package com.company.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentRequestDto {

    String name;
    String surname;
    LocalDate birthdate;

}
