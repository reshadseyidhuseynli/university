package com.company.mapper;

import com.company.dto.AuthorDto;
import com.company.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "books", source = "books")
    AuthorDto toAuthorDto(Author author);

    List<AuthorDto> toAuthorDtoList(List<Author> authors);

}
