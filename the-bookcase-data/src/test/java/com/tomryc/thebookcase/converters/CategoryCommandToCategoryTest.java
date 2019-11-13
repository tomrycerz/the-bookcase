package com.tomryc.thebookcase.converters;

import com.tomryc.thebookcase.commands.CategoryCommand;
import com.tomryc.thebookcase.model.Category;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryCommandToCategoryTest {

    private final static Long LONG_VALUE = 1L;
    private final static String DESCRIPTION = "description";

    private CategoryCommandToCategory converter;

    @BeforeEach
    public void setUp(){
        converter = new CategoryCommandToCategory();
    }

    @AfterEach()
    public void tearDown(){
        converter = null;
    }

    @Test
    public void testNullParameter() throws Exception{
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception{
        assertNotNull(converter.convert(new CategoryCommand()));
    }

    @Test
    public void convert() throws Exception{
        //given
        CategoryCommand command = new CategoryCommand();
        command.setId(LONG_VALUE);
        command.setDescription(DESCRIPTION);

        //when
        Category category = converter.convert(command);

        //then
        assertEquals(LONG_VALUE, category.getId());
        assertEquals(DESCRIPTION, category.getDescription());
    }

}