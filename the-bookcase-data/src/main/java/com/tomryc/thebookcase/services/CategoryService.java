package com.tomryc.thebookcase.services;

import com.tomryc.thebookcase.commands.CategoryCommand;
import com.tomryc.thebookcase.model.Category;

import java.util.Set;

public interface CategoryService {

    Set<CategoryCommand>listAllCategories();

    Category findById(Long id);

    CategoryCommand findCommandById(Long l);

    CategoryCommand saveCategoryCommand(CategoryCommand command);

    void deleteById(Long idToDelete);
}
