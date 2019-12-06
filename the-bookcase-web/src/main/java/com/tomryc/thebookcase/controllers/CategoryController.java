package com.tomryc.thebookcase.controllers;

import com.tomryc.thebookcase.commands.CategoryCommand;
import com.tomryc.thebookcase.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("categories/{id}/show")
    public String showById(@PathVariable String id, Model model){
        model.addAttribute("category", categoryService.findById(Long.valueOf(id)));
        return "category/show";
    }

    @GetMapping("categories/new")
    public String newCategory(Model model){
        model.addAttribute("category", new CategoryCommand());

        return "category/categoryform";
    }

    @GetMapping("categories/{id}/update")
    public String updateCategory(@PathVariable String id, Model model){
        model.addAttribute("category", categoryService.findCommandById(Long.valueOf(id)));

        return "category/categoryform";
    }

    @PostMapping("category")
    public String saveOrUpdate(@ModelAttribute("category") CategoryCommand command){
        CategoryCommand savedCommand = categoryService.saveCategoryCommand(command);

        return "redirect:/categories/" + savedCommand.getId() + "/show";
    }

    @GetMapping("categories/{id}/delete")
    public String deleteById(@PathVariable String id){
        categoryService.deleteById(Long.valueOf(id));

        return "redirect:/";
    }
}
