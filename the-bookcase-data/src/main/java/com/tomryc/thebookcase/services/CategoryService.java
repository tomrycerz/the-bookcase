package com.tomryc.thebookcase.services;

import com.tomryc.thebookcase.commands.CategoryCommand;

import java.util.Set;

public interface CategoryService {

    Set<CategoryCommand>listAllCategories();
}
