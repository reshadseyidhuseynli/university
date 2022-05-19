package com.company.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class AuthorResponseDto {

    private Integer id;
    private String name;
    private List<BookResponseDto> books;

}
