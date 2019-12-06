package com.tomryc.thebookcase.controllers;

import com.tomryc.thebookcase.commands.AuthorCommand;
import com.tomryc.thebookcase.exceptions.NotFoundException;
import com.tomryc.thebookcase.model.Author;
import com.tomryc.thebookcase.services.AuthorService;
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

public class AuthorControllerTest {
    
    @Mock
    AuthorService authorService;

    AuthorController controller;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new AuthorController(authorService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    public void testGetAuthor() throws Exception {

        Author author = new Author();
        author.setId(1L);

        when(authorService.findById(anyLong())).thenReturn(author);

        mockMvc.perform(get("/authors/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("author/show"))
                .andExpect(model().attributeExists("author"));
    }

    @Test
    public void testGetAuthorNotFound() throws Exception {

        when(authorService.findById(anyLong())).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/authors/1/show"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("404error"));
    }

    @Test
    public void testGetAuthorNumberFormatException() throws Exception {

        mockMvc.perform(get("/authors/asdf/show"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("400error"));
    }

    @Test
    public void testGetNewAuthorForm() throws Exception {
        AuthorCommand command = new AuthorCommand();

        mockMvc.perform(get("/authors/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("author/authorform"))
                .andExpect(model().attributeExists("author"));
    }

    @Test
    public void testPostNewAuthorForm() throws Exception {
        AuthorCommand command = new AuthorCommand();
        command.setId(2L);

        when(authorService.saveAuthorCommand(any())).thenReturn(command);

        mockMvc.perform(post("/authors")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("name", "some string")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/authors/2/show"));
    }

/*    @Test
    public void testPostNewAuthorFormValidationFail() throws Exception{

        AuthorCommand command = new AuthorCommand();
        command.setId(2L);

        when(authorService.saveAuthorCommand(any())).thenReturn(command);

        mockMvc.perform(post("/authors")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("name", "Some Name")
        )
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("author"))
                .andExpect(view().name("author/authorform"));

    }*/


    @Test
    public void testGetUpdateView() throws Exception {
        AuthorCommand command = new AuthorCommand();
        command.setId(2L);

        when(authorService.findCommandById(anyLong())).thenReturn(command);

        mockMvc.perform(get("/authors/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("author/authorform"))
                .andExpect(model().attributeExists("author"));
    }

    @Test
    public void testDeleteAction() throws Exception {
        mockMvc.perform(get("/authors/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        verify(authorService, times(1)).deleteById(anyLong());
    }
}
