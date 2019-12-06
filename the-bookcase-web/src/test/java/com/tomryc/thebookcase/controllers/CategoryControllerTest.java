package com.tomryc.thebookcase.controllers;

import com.tomryc.thebookcase.commands.CategoryCommand;
import com.tomryc.thebookcase.exceptions.NotFoundException;
import com.tomryc.thebookcase.model.Category;
import com.tomryc.thebookcase.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CategoryControllerTest {

    @Mock
    CategoryService categoryService;

    CategoryController controller;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new CategoryController(categoryService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    public void testGetCategory() throws Exception {

        Category category = new Category();
        category.setId(1L);

        when(categoryService.findById(anyLong())).thenReturn(category);

        mockMvc.perform(get("/categories/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("category/show"))
                .andExpect(model().attributeExists("category"));
    }

    @Test
    public void testGetCategoryNotFound() throws Exception {

        when(categoryService.findById(anyLong())).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/categories/1/show"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("404error"));
    }

    @Test
    public void testGetCategoryNumberFormatException() throws Exception {

        mockMvc.perform(get("/categories/asdf/show"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("400error"));
    }

    @Test
    public void testGetNewCategoryForm() throws Exception {
        CategoryCommand command = new CategoryCommand();

        mockMvc.perform(get("/categories/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("category/categoryform"))
                .andExpect(model().attributeExists("category"));
    }

    @Test
    public void testPostNewCategoryForm() throws Exception {
        CategoryCommand command = new CategoryCommand();
        command.setId(2L);

        when(categoryService.saveCategoryCommand(any())).thenReturn(command);

        mockMvc.perform(post("/category")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "some string")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/categories/2/show"));
    }

/*    @Test
    public void testPostNewCategoryFormValidationFail() throws Exception{

        CategoryCommand command = new CategoryCommand();
        command.setId(2L);

        when(categoryService.saveCategoryCommand(any())).thenReturn(command);

        mockMvc.perform(post("/categories")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("name", "Some Name")
        )
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("category"))
                .andExpect(view().name("category/categoryform"));

    }*/


    @Test
    public void testGetUpdateView() throws Exception {
        CategoryCommand command = new CategoryCommand();
        command.setId(2L);

        when(categoryService.findCommandById(anyLong())).thenReturn(command);

        mockMvc.perform(get("/categories/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("category/categoryform"))
                .andExpect(model().attributeExists("category"));
    }

    @Test
    public void testDeleteAction() throws Exception {
        mockMvc.perform(get("/categories/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        verify(categoryService, times(1)).deleteById(anyLong());
    }

}
