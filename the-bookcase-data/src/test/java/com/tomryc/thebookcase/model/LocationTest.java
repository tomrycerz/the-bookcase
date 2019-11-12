package com.tomryc.thebookcase.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LocationTest {

    private Location location;

    @BeforeEach
    public void setUp() {
        location = new Location();
    }

    @AfterEach
    public void tearDown() {
        location = null;
    }

    @Test
    public void getDescription() {
        String description = "Front Shelf";
        location.setDescription(description);
        assertEquals(description, location.getDescription());
    }

    @Test
    public void booksEmptyWhenCategoryCreated(){
        assertEquals(0, location.getBooks().size());
    }

    @Test
    public void booksIsOneWhenAdded(){
        location.getBooks().add(new Book());
        assertEquals(1 ,location.getBooks().size());
    }
}