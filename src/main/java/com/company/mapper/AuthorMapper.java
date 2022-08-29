package com.company.mapper;

import com.company.dto.request.AuthorRequestDto;
import com.company.dto.response.AuthorResponseDto;
import com.company.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "books", source = "books")
    AuthorResponseDto toAuthorDto(Author author);

    Author toAuthor(AuthorRequestDto authorRequestDto);

    List<AuthorResponseDto> toAuthorDtoList(List<Author> authors);
}
