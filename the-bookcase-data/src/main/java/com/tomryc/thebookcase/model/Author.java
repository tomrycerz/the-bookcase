package com.tomryc.thebookcase.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Author extends BaseEntity{

    private String name;
    private String surname;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();
}
