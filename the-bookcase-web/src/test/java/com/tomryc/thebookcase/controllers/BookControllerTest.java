package com.tomryc.thebookcase.controllers;

import com.tomryc.thebookcase.commands.BookCommand;
import com.tomryc.thebookcase.exceptions.NotFoundException;
import com.tomryc.thebookcase.model.Book;
import com.tomryc.thebookcase.services.AuthorService;
import com.tomryc.thebookcase.services.BookService;
import com.tomryc.thebookcase.services.CategoryService;
import com.tomryc.thebookcase.services.LocationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BookControllerTest {

    @Mock
    BookService bookService;

    @Mock
    AuthorService authorService;

    @Mock
    CategoryService categoryService;

    @Mock
    LocationService locationService;

    BookController controller;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new BookController(bookService, authorService, categoryService, locationService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    public void testGetBook() throws Exception {

        Book book = new Book();
        book.setId(1L);

        when(bookService.findById(anyLong())).thenReturn(book);

        mockMvc.perform(get("/book/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("book/show"))
                .andExpect(model().attributeExists("book"));
    }

    @Test
    public void testGetBookNotFound() throws Exception {

        when(bookService.findById(anyLong())).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/book/1/show"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("404error"));
    }

    @Test
    public void testGetBookNumberFormatException() throws Exception {

        mockMvc.perform(get("/book/asdf/show"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("400error"));
    }

    @Test
    public void testGetNewBookForm() throws Exception {
        BookCommand command = new BookCommand();

        mockMvc.perform(get("/book/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("book/bookform"))
                .andExpect(model().attributeExists("book"));
    }

    @Test
    public void testPostNewBookForm() throws Exception {
        BookCommand command = new BookCommand();
        command.setId(2L);

        when(bookService.saveBookCommand(any())).thenReturn(command);

        mockMvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("name", "some string")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/book/2/show"));
    }

/*    @Test
    public void testPostNewBookFormValidationFail() throws Exception{

        BookCommand command = new BookCommand();
        command.setId(2L);

        when(bookService.saveBookCommand(any())).thenReturn(command);

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("name", "Some Name")
        )
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("book"))
                .andExpect(view().name("book/bookform"));

    }*/


    @Test
    public void testGetUpdateView() throws Exception {
        BookCommand command = new BookCommand();
        command.setId(2L);

        when(bookService.findCommandById(anyLong())).thenReturn(command);

        mockMvc.perform(get("/book/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("book/bookform"))
                .andExpect(model().attributeExists("book"));
    }

    @Test
    public void testDeleteAction() throws Exception {
        mockMvc.perform(get("/book/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        verify(bookService, times(1)).deleteById(anyLong());
    }
}
