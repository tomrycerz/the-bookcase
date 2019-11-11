package com.tomryc.thebookcase.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();
}
