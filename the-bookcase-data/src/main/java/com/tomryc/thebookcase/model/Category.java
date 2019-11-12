package com.tomryc.thebookcase.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Category extends BaseEntity{

    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private Set<Book> books = new HashSet<>();

}
