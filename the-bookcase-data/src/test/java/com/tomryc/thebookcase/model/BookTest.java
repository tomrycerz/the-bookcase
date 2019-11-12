package com.tomryc.thebookcase.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    private Book book;

    @BeforeEach
    void setUp() {
        book = new Book();
    }

    @AfterEach
    void tearDown() {
        book = null;
    }

    @Test
    public void getId(){
        Long idValue = 4L;
        book.setId(idValue);
        assertEquals(idValue, book.getId());
    }

    @Test
    public void getTitle() {
        String title = "Title";
        book.setTitle(title);
        assertEquals(title, book.getTitle());
    }

    @Test
    public void getIsbn() {
        String isbn = "0000-ASD-0000";
        book.setIsbn(isbn);
        assertEquals(isbn, book.getIsbn());
    }

    @Test
    public void getCategory() {
        Category category = new Category();
        book.setCategory(category);
        assertEquals(category, book.getCategory());
    }

    @Test
    public void authorssEmptyWhenBookCreated(){
        assertEquals(0, book.getAuthors().size());
    }

    @Test
    public void authorsIsOneWhenAdded(){
        book.getAuthors().add(new Author());
        assertEquals(1,book.getAuthors().size());
    }
}