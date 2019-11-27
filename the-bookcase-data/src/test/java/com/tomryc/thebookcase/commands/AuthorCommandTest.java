package com.tomryc.thebookcase.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AuthorCommandTest {

    private AuthorCommand authorCommand;

    @BeforeEach
    public void setUp() {
        authorCommand = new AuthorCommand();
    }

    @Test
    public void getId() {
        Long idValue = 4L;
        authorCommand.setId(idValue);
        assertEquals(idValue, authorCommand.getId());
    }

    @Test
    public void getName() {
        String name = "John Rambo";
        authorCommand.setName(name);
        assertEquals(name, authorCommand.getName());
    }

}