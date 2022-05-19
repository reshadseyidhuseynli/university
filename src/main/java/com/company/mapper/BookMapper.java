package com.company.mapper;

import com.company.dto.response.BookResponseDto;
import com.company.entity.Book;
import com.company.mapper.qualifier.EnumMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = EnumMapper.class)
public interface BookMapper {

    BookResponseDto toBookDto(Book book);

    List<BookResponseDto> toBookDtoList(List<Book> books);

}
