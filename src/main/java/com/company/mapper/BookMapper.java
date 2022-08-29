package com.company.mapper;

import com.company.dto.request.BookRequestDto;
import com.company.dto.response.BookResponseDto;
import com.company.entity.Book;
import com.company.mapper.qualifier.EnumMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = EnumMapper.class)
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookResponseDto toBookDto(Book book);

    Book toBook(BookRequestDto bookRequestDto);

    List<BookResponseDto> toBookDtoList(List<Book> books);

}
