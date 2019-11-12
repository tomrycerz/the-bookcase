package com.tomryc.thebookcase.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Book extends BaseEntity{

    private String title;
    private String isbn;

    @ManyToMany
    @OrderBy("surname")
    @JoinTable(name="book_authors",
        joinColumns = @JoinColumn(name="book_id"),
            inverseJoinColumns = @JoinColumn(name="author_id"))
    private Set<Author> authors = new HashSet<>();

    @ManyToOne
    private Category category;

}

