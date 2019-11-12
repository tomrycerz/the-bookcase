package com.tomryc.thebookcase.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {

    private Category category;

    @BeforeEach
    void setUp() {
        category = new Category();
    }

    @AfterEach
    void tearDown() {
        category = null;
    }

    @Test
    void getDescription() {
        String description = "Description";
        category.setDescription(description);
        assertEquals(description, category.getDescription());
    }

    @Test
    public void booksEmptyWhenCategoryCreated(){
        assertEquals(0, category.getBooks().size());
    }

    @Test
    public void booksIsOneWhenAdded(){
        category.getBooks().add(new Book());
        assertEquals(1 ,category.getBooks().size());
    }
}