package com.tomryc.thebookcase.services;

import com.tomryc.thebookcase.commands.AuthorCommand;
import com.tomryc.thebookcase.converters.AuthorCommandToAuthor;
import com.tomryc.thebookcase.converters.AuthorToAuthorCommand;
import com.tomryc.thebookcase.model.Author;
import com.tomryc.thebookcase.repositories.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthorServiceImplTest {

    private AuthorServiceImpl authorService;

    @Mock
    AuthorRepository authorRepository;

    @Mock
    AuthorToAuthorCommand authorToAuthorCommand;

    @Mock
    AuthorCommandToAuthor authorCommandToAuthor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        authorService = new AuthorServiceImpl(authorRepository, authorToAuthorCommand, authorCommandToAuthor);
    }

    @Test
    public void getAuthorsTest() throws Exception{
        Author author = new Author();
        HashSet<Author> authorData = new HashSet<>();

        authorData.add(author);

        when(authorService.getAuthors()).thenReturn(authorData);

        Set<Author> authors = authorService.getAuthors();

        assertEquals(1,authors.size());
        verify(authorRepository, times(1)).findAll();
        verify(authorRepository, never()).findById(anyLong());
    }

    @Test
    public void getAuthorByIdTest() throws Exception{
        Author author = new Author();
        author.setId(1L);
        Optional<Author> optionalAuthor = Optional.of(author);

        when(authorRepository.findById(anyLong())).thenReturn(optionalAuthor);

        Author returneAuthor = authorService.findById(1l);

        assertNotNull(returneAuthor, "Null Author returned");
        verify(authorRepository, times(1)).findById(anyLong());
        verify(authorRepository, never()).findAll();
    }

    @Test
    public void getAuthorCommandByIdTest() throws Exception{
        Author author = new Author();
        author.setId(1L);
        Optional<Author> authorOptional = Optional.of(author);

        when(authorRepository.findById(anyLong())).thenReturn(authorOptional);

        AuthorCommand authorCommand = new AuthorCommand();
        authorCommand.setId(1L);

        when(authorToAuthorCommand.convert(any())).thenReturn(authorCommand);

        AuthorCommand commandById = authorService.findCommandById(1L);

        assertNotNull(commandById, "Null author returned");
        verify(authorRepository, times(1)).findById(anyLong());
        verify(authorRepository, never()).findAll();
    }

    @Test
    public void deleteByIdTest() throws Exception{

        Long idToDelete = 1L;

        //when
        authorService.deleteById(idToDelete);

        //then
        verify(authorRepository, times(1)).deleteById(anyLong());
    }
}