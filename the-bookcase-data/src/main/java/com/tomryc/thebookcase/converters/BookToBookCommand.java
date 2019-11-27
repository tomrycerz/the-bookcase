package com.tomryc.thebookcase.converters;

import com.tomryc.thebookcase.commands.BookCommand;
import com.tomryc.thebookcase.model.Book;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class BookToBookCommand implements Converter<Book, BookCommand> {

    private final CategoryToCategoryCommand categoryConverter;
    private final AuthorToAuthorCommand authorConverter;
    private final LocationToLocationCommand locationConverter;

    public BookToBookCommand(CategoryToCategoryCommand categoryConverter, AuthorToAuthorCommand authorConverter, LocationToLocationCommand locationConverter) {
        this.categoryConverter = categoryConverter;
        this.authorConverter = authorConverter;
        this.locationConverter = locationConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public BookCommand convert(Book source){
        if(source == null){
            return null;
        }
        final BookCommand bookCommand = new BookCommand();
        bookCommand.setId(source.getId());
        bookCommand.setTitle(source.getTitle());
        bookCommand.setIsbn(source.getIsbn());
        bookCommand.setCategory(categoryConverter.convert(source.getCategory()));
        bookCommand.setUrl(source.getUrl());

        if(source.getAuthor() != null){
            bookCommand.setAuthor(authorConverter.convert(source.getAuthor()));
        }

        if(source.getLocation() != null){
            bookCommand.setLocation(locationConverter.convert(source.getLocation()));
        }

        return bookCommand;
    }
}
