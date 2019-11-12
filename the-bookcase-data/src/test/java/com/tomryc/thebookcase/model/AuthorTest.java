package com.tomryc.thebookcase.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthorTest {

    private Author author;

    @BeforeEach
    public void setUp(){
        author = new Author();
    }

    @Test
    public void getId(){
        Long idValue = 4L;
        author.setId(idValue);
        assertEquals(idValue, author.getId());
    }

    @Test
    public void getName() {
        String name = "John";
        author.setName(name);
        assertEquals(name, author.getName());
    }

    @Test
    public void getSurname() {
        String surname = "Rambo";
        author.setSurname(surname);
        assertEquals(surname, author.getSurname());
    }

    @Test
    public void booksEmptyWhenAuthorCreated(){
        assertEquals(0, author.getBooks().size());
    }

    @Test
    public void booksIsOneWhenAdded(){
        author.getBooks().add(new Book());
        assertEquals(1,author.getBooks().size());
    }
}