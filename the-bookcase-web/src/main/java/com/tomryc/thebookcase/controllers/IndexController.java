package com.tomryc.thebookcase.controllers;

import com.tomryc.thebookcase.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private final BookService bookService;

    public IndexController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model){
        model.addAttribute("books", bookService.getBooks());

        return "index";
    }
}
