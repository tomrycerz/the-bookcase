package com.tomryc.thebookcase.services;

import com.tomryc.thebookcase.commands.AuthorCommand;
import com.tomryc.thebookcase.model.Author;

import java.util.Set;

public interface AuthorService {

    Set<Author> getAuthors();

    Author findById(Long l);

    AuthorCommand findCommandById(Long l);

    AuthorCommand saveAuthorCommand(AuthorCommand command);

    void deleteById(Long idToDelete);


}
