package com.company.dto;

import com.company.database.entity.Author;
import com.company.util.abstraction.IdentifierDTO;

public class AuthorDTO extends IdentifierDTO {

    public AuthorDTO() {
    }

    public AuthorDTO(Author author) {
        setId(author.getId());
        setName(author.getName());
    }
}
