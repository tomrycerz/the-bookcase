package com.tomryc.thebookcase.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Category extends BaseEntity{

    private String description;

    @OneToMany(mappedBy = "category")
    private Set<Book> books = new HashSet<>();

}
