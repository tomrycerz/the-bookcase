package com.tomryc.thebookcase.commands;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryCommandTest {

    private CategoryCommand categoryCommand;

    @BeforeEach
    void setUp() {
        categoryCommand = new CategoryCommand();
    }

    @AfterEach
    void tearDown() {
        categoryCommand = null;
    }

    @Test
    void getDescription() {
        String description = "Description";
        categoryCommand.setDescription(description);
        assertEquals(description, categoryCommand.getDescription());
    }

}