package com.tomryc.thebookcase.services;


import com.tomryc.thebookcase.commands.CategoryCommand;
import com.tomryc.thebookcase.converters.CategoryCommandToCategory;
import com.tomryc.thebookcase.converters.CategoryToCategoryCommand;
import com.tomryc.thebookcase.model.Category;
import com.tomryc.thebookcase.repositories.CategoryRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CategoryServiceImplTest {

    CategoryToCategoryCommand categoryToCategoryCommand = new CategoryToCategoryCommand();
    CategoryService service;

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    CategoryCommandToCategory categoryCommandToCategory;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        service = new CategoryServiceImpl(categoryRepository, categoryToCategoryCommand, categoryCommandToCategory);
    }

    @Test
    public void listAllCategories() throws Exception {
        //given
        Set<Category> categorys = new HashSet<>();
        Category category1 = new Category();
        category1.setId(1L);
        categorys.add(category1);

        Category category2 = new Category();
        category2.setId(2L);
        categorys.add(category2);

        when(categoryRepository.findAll()).thenReturn(categorys);

        //when
        Set<CategoryCommand> commands = service.listAllCategories();

        //then
        assertEquals(2, commands.size());
        verify(categoryRepository, times(1)).findAll();
    }

}