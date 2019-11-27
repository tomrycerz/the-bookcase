package com.tomryc.thebookcase.converters;

import com.tomryc.thebookcase.commands.AuthorCommand;
import com.tomryc.thebookcase.model.Author;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AuthorCommandToAuthorTest {

    public static final String NAME = "name surname";
    public static final Long LONG_VALUE = 1L;

    private AuthorCommandToAuthor converter;

    @BeforeEach
    public void setUp() {
        converter = new AuthorCommandToAuthor();
    }

    @AfterEach
    public void tearDown() {
        converter = null;
    }

    @Test
    public void testNullParameter() throws Exception{
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception{
        assertNotNull(converter.convert(new AuthorCommand()));
    }

    @Test
    public void convert() throws Exception{
        //given
        AuthorCommand command = new AuthorCommand();
        command.setId(LONG_VALUE);
        command.setName(NAME);

        //when
        Author author = converter.convert(command);

        //then
        assertNotNull(author);
        assertEquals(LONG_VALUE, author.getId());
        assertEquals(NAME, author.getName());
    }
}