package com.company.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TeacherRequestDto {

    String name;
    String surname;
    LocalDate birthdate;

}
