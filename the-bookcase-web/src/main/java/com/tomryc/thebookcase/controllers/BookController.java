package com.tomryc.thebookcase.controllers;

import com.tomryc.thebookcase.commands.BookCommand;
import com.tomryc.thebookcase.services.AuthorService;
import com.tomryc.thebookcase.services.BookService;
import com.tomryc.thebookcase.services.CategoryService;
import com.tomryc.thebookcase.services.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final LocationService locationService;

    public BookController(BookService bookService, AuthorService authorService, CategoryService categoryService, LocationService locationService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.locationService = locationService;
    }

    @GetMapping("book/{id}/show")
    public String showById(@PathVariable String id, Model model){

        model.addAttribute("book", bookService.findById(Long.valueOf(id)));

        return "book/show";
    }

    @GetMapping("book/new")
    public String newBook(Model model){
        model.addAttribute("book", new BookCommand());
        model.addAttribute("categories", categoryService.listAllCategories());
        model.addAttribute("locations", locationService.listAllLocations());
        model.addAttribute("authors", authorService.getAuthors());

        return "book/bookform";
    }

    @GetMapping("book/{id}/update")
    public String updateBook(@PathVariable String id, Model model){
        model.addAttribute("book", bookService.findCommandById(Long.valueOf(id)));
        model.addAttribute("categories", categoryService.listAllCategories());
        model.addAttribute("locations", locationService.listAllLocations());
        model.addAttribute("authors", authorService.getAuthors());

        return "book/bookform";
    }

    @PostMapping("book")
    public String saveOrUpdate(@ModelAttribute("book") BookCommand command){

        BookCommand savedCommand = bookService.saveBookCommand(command);

        return "redirect:/book/" + savedCommand.getId() + "/show";
    }

    @GetMapping("book/{id}/delete")
    public String deleteById(@PathVariable String id){

        bookService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }
}
