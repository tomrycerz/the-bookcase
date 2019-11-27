package com.tomryc.thebookcase.converters;

import com.tomryc.thebookcase.commands.AuthorCommand;
import com.tomryc.thebookcase.commands.BookCommand;
import com.tomryc.thebookcase.commands.CategoryCommand;
import com.tomryc.thebookcase.model.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookCommandToBookTest {

    private BookCommandToBook converter;

    private static final Long BOOK_ID = 1L;
    private static final String TITLE = "title";
    private static final String ISBN = "isbn";
    private static final Long CAT_ID = 1L;
    private static final String CAT_DESCRIPTION = "description";
    private static final Long AUTH_ID1 = 1L;

    @BeforeEach
    public void setUp(){
        converter = new BookCommandToBook(new CategoryCommandToCategory(), new AuthorCommandToAuthor(), new LocationCommandToLocation());
    }

    @AfterEach
    public void tearDwn(){
        converter = null;
    }

    @Test
    public void testNullParameter(){
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject(){
        assertNotNull(converter.convert(new BookCommand()));
    }

    @Test
    public void convert(){
        //given
        BookCommand bookcommand = new BookCommand();
        bookcommand.setId(BOOK_ID);
        bookcommand.setTitle(TITLE);
        bookcommand.setIsbn(ISBN);

        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(CAT_ID);
        categoryCommand.setDescription(CAT_DESCRIPTION);

        bookcommand.setCategory(categoryCommand);

        AuthorCommand authorCommand1 = new AuthorCommand();
        authorCommand1.setId(AUTH_ID1);

        bookcommand.setAuthor(authorCommand1);

        //when
        Book book = converter.convert(bookcommand);

        //then
        assertNotNull(book);
        assertEquals(BOOK_ID, book.getId());
        assertEquals(TITLE, book.getTitle());
        assertEquals(ISBN, book.getIsbn());
        assertEquals(CAT_ID, book.getCategory().getId());
        assertEquals(CAT_DESCRIPTION, book.getCategory().getDescription());
        assertEquals(AUTH_ID1, book.getAuthor().getId());


    }

}