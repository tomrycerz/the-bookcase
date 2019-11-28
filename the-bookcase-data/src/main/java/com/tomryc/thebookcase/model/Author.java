package com.tomryc.thebookcase.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@EqualsAndHashCode(callSuper = true, exclude = {"books"})
@Entity
@ToString
public class Author extends BaseEntity{

    private String name;
    private String url;

    @OrderBy("name")
    @OneToMany(mappedBy = "author")
    private Set<Book> books = new HashSet<>();

}
