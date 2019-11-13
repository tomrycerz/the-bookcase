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

    public BookCommandToBook(CategoryCommandToCategory categoryConverter, AuthorCommandToAuthor authorConverter) {
        this.categoryConverter = categoryConverter;
        this.authorConverter = authorConverter;
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

        if(source.getAuthors() != null && source.getAuthors().size() > 0){
            source.getAuthors()
                    .forEach(author -> book.getAuthors().add(authorConverter.convert(author)));
        }

        return book;
    }
}
