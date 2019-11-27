package com.tomryc.thebookcase.commands;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookCommandTest {

    private BookCommand bookCommand;

    @BeforeEach
    void setUp() {
        bookCommand = new BookCommand();
    }

    @AfterEach
    void tearDown() {
        bookCommand = null;
    }

    @Test
    public void getId(){
        Long idValue = 4L;
        bookCommand.setId(idValue);
        assertEquals(idValue, bookCommand.getId());
    }

    @Test
    public void getTitle() {
        String title = "Title";
        bookCommand.setTitle(title);
        assertEquals(title, bookCommand.getTitle());
    }

    @Test
    public void getIsbn() {
        String isbn = "0000-ASD-0000";
        bookCommand.setIsbn(isbn);
        assertEquals(isbn, bookCommand.getIsbn());
    }

    @Test
    public void getCategory() {
        CategoryCommand categoryCommand = new CategoryCommand();
        bookCommand.setCategory(categoryCommand);
        assertEquals(categoryCommand, bookCommand.getCategory());
    }

    @Test
    public void getAuthors(){
        AuthorCommand authorCommand = new AuthorCommand();
        bookCommand.setAuthor(authorCommand);
        assertEquals(authorCommand, bookCommand.getAuthor());
    }

}