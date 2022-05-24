package com.company.mapper;

import com.company.dto.response.AuthorResponseDto;
import com.company.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "books", source = "books")
    AuthorResponseDto toAuthorDto(Author author);

    List<AuthorResponseDto> toAuthorDtoList(List<Author> authors);

}
