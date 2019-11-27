package com.tomryc.thebookcase.converters;

import com.tomryc.thebookcase.commands.AuthorCommand;
import com.tomryc.thebookcase.model.Author;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AuthorToAuthorCommandTest {

    public static final String NAME = "name surname";
    public static final Long LONG_VALUE = 1L;

    private AuthorToAuthorCommand converter;

    @BeforeEach
    public void setUp() {
        converter = new AuthorToAuthorCommand();
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
        assertNotNull(converter.convert(new Author()));
    }

    @Test
    public void convert() throws Exception{
        //given
        Author author = new Author();
        author.setId(LONG_VALUE);
        author.setName(NAME);


        //when
        AuthorCommand command = converter.convert(author);

        //then
        assertNotNull(command);
        assertEquals(LONG_VALUE, command.getId());
        assertEquals(NAME, command.getName());
    }

}