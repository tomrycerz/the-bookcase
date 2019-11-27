package com.tomryc.thebookcase.converters;

import com.tomryc.thebookcase.commands.BookCommand;
import com.tomryc.thebookcase.model.Book;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class BookCommandToBook implements Converter<BookCommand, Book> {

    private final CategoryCommandToCategory categoryConverter;
    private final AuthorCommandToAuthor authorConverter;
    private final LocationCommandToLocation locationConverter;

    public BookCommandToBook(CategoryCommandToCategory categoryConverter, AuthorCommandToAuthor authorConverter, LocationCommandToLocation locationConverter) {
        this.categoryConverter = categoryConverter;
        this.authorConverter = authorConverter;
        this.locationConverter = locationConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Book convert(BookCommand source){
        if(source == null){
            return null;
        }
        final Book book = new Book();

        book.setId(source.getId());
        book.setTitle(source.getTitle());
        book.setCategory(categoryConverter.convert(source.getCategory()));
        book.setIsbn(source.getIsbn());
        book.setUrl(source.getUrl());

        if(source.getAuthor() != null){
            book.setAuthor(authorConverter.convert(source.getAuthor()));
        }

        if(source.getLocation() != null){
            book.setLocation(locationConverter.convert(source.getLocation()));
        }
        return book;
    }
}
