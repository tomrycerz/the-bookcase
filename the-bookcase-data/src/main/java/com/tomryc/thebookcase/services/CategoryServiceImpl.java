package com.tomryc.thebookcase.services;

import com.tomryc.thebookcase.commands.CategoryCommand;
import com.tomryc.thebookcase.converters.CategoryCommandToCategory;
import com.tomryc.thebookcase.converters.CategoryToCategoryCommand;
import com.tomryc.thebookcase.model.Category;
import com.tomryc.thebookcase.repositories.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryToCategoryCommand categoryToCategoryCommand;
    private final CategoryCommandToCategory categoryCommandToCategory;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryToCategoryCommand categoryToCategoryCommand, CategoryCommandToCategory categoryCommandToCategory) {
        this.categoryRepository = categoryRepository;
        this.categoryToCategoryCommand = categoryToCategoryCommand;
        this.categoryCommandToCategory = categoryCommandToCategory;
    }

    @Override
    public Set<CategoryCommand> listAllCategories() {
        return StreamSupport.stream(categoryRepository.findAll()
                .spliterator(), false)
                .map(categoryToCategoryCommand::convert)
                .collect(Collectors.toSet());

    }

    @Override
    public Category findById(Long id){

        Optional<Category> locationOptional = categoryRepository.findById(id);

        if(!locationOptional.isPresent()){
            // TODO: 28/11/2019 add NotFoundException(RunTime)exception
            //throw new Exception();
        }
        return locationOptional.get();
    }

    @Override
    @Transactional
    public CategoryCommand findCommandById(Long l) {
        return categoryToCategoryCommand.convert(findById(l));
    }

    @Override
    @Transactional
    public CategoryCommand saveCategoryCommand(CategoryCommand command) {

        Category detachedCategory = categoryCommandToCategory.convert(command);
        Category savedCategory = categoryRepository.save(detachedCategory);
        log.debug("Saved Category Id: " + savedCategory.getId());

        return categoryToCategoryCommand.convert(savedCategory);
    }

    @Override
    public void deleteById(Long idToDelete) {
        categoryRepository.deleteById(idToDelete);
    }
}
