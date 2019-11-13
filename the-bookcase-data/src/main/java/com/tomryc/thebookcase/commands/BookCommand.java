package com.tomryc.thebookcase.commands;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class BookCommand {

    private Long id;
    private String title;
    private String isbn;
    private CategoryCommand category;
    private Set<AuthorCommand> authors = new HashSet<>();

}
