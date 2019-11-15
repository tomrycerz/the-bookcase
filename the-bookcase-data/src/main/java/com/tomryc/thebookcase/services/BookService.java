package com.tomryc.thebookcase.services;

import com.tomryc.thebookcase.commands.BookCommand;
import com.tomryc.thebookcase.model.Book;

import java.util.Set;

public interface BookService {

    Set<Book> getBooks();

    Book findById(Long l);

    BookCommand findCommandById(Long l);

    BookCommand saveBookCommand(BookCommand command);

    void deleteById(Long idToDelete);

}
