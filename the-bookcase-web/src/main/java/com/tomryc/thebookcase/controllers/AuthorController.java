package com.tomryc.thebookcase.controllers;

import com.tomryc.thebookcase.commands.AuthorCommand;
import com.tomryc.thebookcase.services.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }


    @GetMapping("authors/{id}/show")
    public String showById(@PathVariable String id, Model model){
        model.addAttribute("author", authorService.findById(Long.valueOf(id)));

        return "author/show";
    }

    @GetMapping("authors/new")
    public String newAuthor(Model model){
        model.addAttribute("author", new AuthorCommand());

        return "author/authorform";
    }

    @GetMapping("authors/{id}/update")
    public String updateAuthor(@PathVariable String id, Model model){
        model.addAttribute("author", authorService.findCommandById(Long.valueOf(id)));

        return "author/authorform";
    }

    @PostMapping("authors")
    public String saveOrUpdate(@ModelAttribute("author") AuthorCommand command){
        AuthorCommand savedCommand = authorService.saveAuthorCommand(command);

        return "redirect:/authors/" + savedCommand.getId() + "/show";
    }

    @GetMapping("authors/{id}/delete")
    public String deleteById(@PathVariable("id") String id){
        authorService.deleteById(Long.valueOf(id));

        return "redirect:/";
    }
}
