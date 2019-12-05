package com.tomryc.thebookcase.services;

import com.tomryc.thebookcase.commands.BookCommand;
import com.tomryc.thebookcase.converters.BookCommandToBook;
import com.tomryc.thebookcase.converters.BookToBookCommand;
import com.tomryc.thebookcase.model.Book;
import com.tomryc.thebookcase.repositories.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyLong;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookServiceImplTest {

    BookServiceImpl bookService;

    @Mock
    BookRepository bookRepository;

    @Mock
    BookToBookCommand bookToBookCommand;

    @Mock
    BookCommandToBook bookCommandToBook;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        bookService = new BookServiceImpl(bookRepository, bookToBookCommand, bookCommandToBook);
    }

    @Test
    public void getBooksTest() throws Exception{

        Book book = new Book();
        HashSet<Book> bookData = new HashSet<>();
        bookData.add(book);

        when(bookService.getBooks()).thenReturn(bookData);

        Set<Book> books = bookService.getBooks();

        assertEquals(1,books.size());
        verify(bookRepository, times(1)).findAll();
        verify(bookRepository, never()).findById(anyLong());

    }


    @Test
    public void getBookByIdTest() throws Exception {

        Book book = new Book();
        book.setId(1L);
        Optional<Book> bookOptional = Optional.of(book);

        when(bookRepository.findById(anyLong())).thenReturn(bookOptional);

        Book bookReturned = bookService.findById(1L);

        assertNotNull(bookReturned, "Null book returned");
        verify(bookRepository, times(1)).findById(anyLong());
        verify(bookRepository, never()).findAll();
    }

    @Test
    public void getBookCommandByIdTest() throws Exception {
        Book book = new Book();
        book.setId(1L);
        Optional<Book> bookOptional = Optional.of(book);

        when(bookRepository.findById(anyLong())).thenReturn(bookOptional);

        BookCommand bookCommand = new BookCommand();
        bookCommand.setId(1L);

        when(bookToBookCommand.convert(any())).thenReturn(bookCommand);

        BookCommand commandById = bookService.findCommandById(1L);

        assertNotNull(commandById,"Null book returned");
        verify(bookRepository, times(1)).findById(anyLong());
        verify(bookRepository, never()).findAll();
    }

    @Test
    public void deleteByIdTest(){

        Long idToDelete = 1L;

        //when
        bookService.deleteById(1l);

        //then
        verify(bookRepository, times(1)).deleteById(anyLong());
    }
}