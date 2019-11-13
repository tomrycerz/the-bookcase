package com.tomryc.thebookcase.commands;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationCommandTest {

    private LocationCommand locationCommand;

    @BeforeEach
    public void setUp() {
        locationCommand = new LocationCommand();
    }

    @AfterEach
    public void tearDown() {
        locationCommand = null;
    }

    @Test
    public void getDescription() {
        String description = "Front Shelf";
        locationCommand.setDescription(description);
        assertEquals(description, locationCommand.getDescription());
    }

}