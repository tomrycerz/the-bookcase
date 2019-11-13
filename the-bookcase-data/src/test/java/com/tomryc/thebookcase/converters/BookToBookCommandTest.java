package com.tomryc.thebookcase.converters;

import com.tomryc.thebookcase.commands.BookCommand;
import com.tomryc.thebookcase.model.Author;
import com.tomryc.thebookcase.model.Book;
import com.tomryc.thebookcase.model.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookToBookCommandTest {

    private BookToBookCommand converter;
    private static final Long BOOK_ID = 1L;
    private static final String TITLE = "title";
    private static final String ISBN = "isbn";
    private static final Long CAT_ID = 1L;
    private static final String CAT_DESCRIPTION = "description";
    private static final Long AUTH_ID1 = 1L;
    private static final Long AUTH_ID2 = 2L;

    @BeforeEach
    public void setUp(){
        converter = new BookToBookCommand(new CategoryToCategoryCommand(), new AuthorToAuthorCommand());
    }

    @AfterEach
    public void tearDown(){
        converter = null;
    }

    @Test
    public void testNullParameter(){
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject(){
        assertNotNull(converter.convert(new Book()));
    }

    @Test
    public void convert(){
        //given
        Book book = new Book();
        book.setId(BOOK_ID);
        book.setTitle(TITLE);
        book.setIsbn(ISBN);

        Category category = new Category();
        category.setId(CAT_ID);
        category.setDescription(CAT_DESCRIPTION);

        book.setCategory(category);

        Author author1 = new Author();
        author1.setId(AUTH_ID1);

        Author author2 = new Author();
        author2.setId(AUTH_ID2);

        book.getAuthors().add(author1);
        book.getAuthors().add(author2);

        //when
        BookCommand bookCommand = converter.convert(book);

        //then
        assertNotNull(bookCommand);
        assertEquals(BOOK_ID, bookCommand.getId());
        assertEquals(TITLE, bookCommand.getTitle());
        assertEquals(ISBN, bookCommand.getIsbn());
        assertEquals(CAT_ID, bookCommand.getCategory().getId());
        assertEquals(CAT_DESCRIPTION, bookCommand.getCategory().getDescription());
        assertEquals(2, book.getAuthors().size());

    }

}