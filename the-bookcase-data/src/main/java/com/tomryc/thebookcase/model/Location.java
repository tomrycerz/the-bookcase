package com.tomryc.thebookcase.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Location extends BaseEntity{

    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Book> books = new HashSet<>();
}
