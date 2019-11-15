package com.tomryc.thebookcase.services;

import com.tomryc.thebookcase.commands.CategoryCommand;
import com.tomryc.thebookcase.converters.CategoryToCategoryCommand;
import com.tomryc.thebookcase.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryToCategoryCommand categoryToCategoryCommand;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryToCategoryCommand categoryToCategoryCommand) {
        this.categoryRepository = categoryRepository;
        this.categoryToCategoryCommand = categoryToCategoryCommand;
    }

    @Override
    public Set<CategoryCommand> listAllCategories() {
        return StreamSupport.stream(categoryRepository.findAll()
                .spliterator(), false)
                .map(categoryToCategoryCommand::convert)
                .collect(Collectors.toSet());

    }
}
