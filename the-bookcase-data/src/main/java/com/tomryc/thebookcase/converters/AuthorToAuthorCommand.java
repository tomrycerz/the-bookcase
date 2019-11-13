package com.tomryc.thebookcase.converters;

import com.tomryc.thebookcase.commands.AuthorCommand;
import com.tomryc.thebookcase.model.Author;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class AuthorToAuthorCommand implements Converter<Author, AuthorCommand> {

    @Synchronized
    @Nullable
    @Override
    public AuthorCommand convert(Author source) {
        if(source == null) {
            return null;
        }
        final AuthorCommand authorCommand = new AuthorCommand();

        authorCommand.setId(source.getId());
        authorCommand.setName(source.getName());
        authorCommand.setSurname(source.getSurname());

        return authorCommand;
    }
}
