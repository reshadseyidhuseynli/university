package com.company.mapper;

import com.company.dto.response.AuthorResponseDto;
import com.company.entity.Author;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorResponseDto toAuthorDto(Author author);

    List<AuthorResponseDto> toAuthorDtoList(List<Author> authors);

}
