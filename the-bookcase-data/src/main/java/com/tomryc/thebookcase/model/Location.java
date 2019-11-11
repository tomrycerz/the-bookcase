package com.tomryc.thebookcase.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Book> books = new HashSet<>();
}
