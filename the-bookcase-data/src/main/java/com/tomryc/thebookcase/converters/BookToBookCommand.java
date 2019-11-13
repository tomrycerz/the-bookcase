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

    public BookToBookCommand(CategoryToCategoryCommand categoryConverter, AuthorToAuthorCommand authorConverter) {
        this.categoryConverter = categoryConverter;
        this.authorConverter = authorConverter;
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

        if(source.getAuthors() != null && source.getAuthors().size() > 0){
            source.getAuthors()
                    .forEach(author -> bookCommand.getAuthors().add(authorConverter.convert(author)));
        }

        return bookCommand;
    }
}
