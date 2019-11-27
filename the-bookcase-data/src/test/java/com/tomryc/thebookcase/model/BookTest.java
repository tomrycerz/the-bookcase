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
    public void getUrl(){
        String url = "https://www.url.com";
        book.setUrl(url);
        assertEquals(url, book.getUrl());
    }

    @Test
    public void getAuthor(){
        Author author = new Author();
        book.setAuthor(author);
        assertEquals(author, book.getAuthor());
    }

    @Test
    public void getCategory() {
        Category category = new Category();
        book.setCategory(category);
        assertEquals(category, book.getCategory());
    }

}