package com.tomryc.thebookcase.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Book extends BaseEntity{

    private String title;
    private String isbn;
    private String url;

    @ManyToOne
    @JoinTable(name="book_author",
        joinColumns = @JoinColumn(name="book_id"),
            inverseJoinColumns = @JoinColumn(name="author_id"))
    private Author author = new Author();

    @ManyToOne
    private Category category;

    @ManyToOne
    private Location location;
}

