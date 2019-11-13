package com.tomryc.thebookcase.converters;

import com.tomryc.thebookcase.commands.AuthorCommand;
import com.tomryc.thebookcase.model.Author;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class AuthorCommandToAuthor implements Converter<AuthorCommand, Author> {

    @Synchronized
    @Nullable
    @Override
    public Author convert(AuthorCommand source) {
        if(source == null) {
            return null;
        }
        final Author author = new Author();

        author.setId(source.getId());
        author.setName(source.getName());
        author.setSurname(source.getSurname());

        return author;
    }
}
