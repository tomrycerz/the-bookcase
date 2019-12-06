package com.tomryc.thebookcase.services;

import com.tomryc.thebookcase.commands.BookCommand;
import com.tomryc.thebookcase.converters.BookCommandToBook;
import com.tomryc.thebookcase.converters.BookToBookCommand;
import com.tomryc.thebookcase.exceptions.NotFoundException;
import com.tomryc.thebookcase.model.Book;
import com.tomryc.thebookcase.repositories.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private final BookToBookCommand bookToBookCommand;
    private final BookCommandToBook bookCommandToBook;

    public BookServiceImpl(BookRepository bookRepository, BookToBookCommand bookToBookCommand, BookCommandToBook bookCommandToBook) {
        this.bookRepository = bookRepository;
        this.bookToBookCommand = bookToBookCommand;
        this.bookCommandToBook = bookCommandToBook;
    }

    @Override
    public Set<Book> getBooks() {

        Set<Book> bookSet = new HashSet<>();
        bookRepository.findAll().iterator().forEachRemaining(bookSet::add);

        return bookSet;
    }

    @Override
    public Book findById(Long l) {
        Optional<Book> bookOptional = bookRepository.findById(l);

        if(!bookOptional.isPresent()) {
            throw new NotFoundException("Book not found. For ID value: " + l.toString());
        }
            return bookOptional.get();
    }

    @Override
    @Transactional
    public BookCommand findCommandById(Long l) {
        return bookToBookCommand.convert(findById(l));
    }

    @Override
    @Transactional
    public BookCommand saveBookCommand(BookCommand command) {

        Book detachedBook = bookCommandToBook.convert(command);
        Book savedBook = bookRepository.save(detachedBook);
        log.debug("Saved BookId: " + savedBook.getId());
        return bookToBookCommand.convert(savedBook);
    }

    @Override
    @Transactional
    public void deleteById(Long idToDelete) {
        bookRepository.deleteById(idToDelete);
    }
}
