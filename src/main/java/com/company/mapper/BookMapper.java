package com.company.mapper;

import com.company.dto.BookDto;
import com.company.entity.Book;
import com.company.mapper.qualifier.EnumMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = EnumMapper.class)
public interface BookMapper {

    BookDto toBookDto(Book book);

    List<BookDto> toBookDtoList(List<Book> books);

}
