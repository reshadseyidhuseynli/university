package com.company.dto.response;

import com.company.domain.BookType;
import lombok.Data;

@Data
public class BookResponseDto {

    private Integer id;
    private String name;
    private AuthorResponseDto author;
    private BookType type;
    private Integer page;
    private boolean hardCover;

}
