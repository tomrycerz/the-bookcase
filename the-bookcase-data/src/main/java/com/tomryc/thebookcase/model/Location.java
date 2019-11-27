package com.tomryc.thebookcase.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Location extends BaseEntity{

    private String description;

    @OneToMany(mappedBy="location")
    private Set<Book> books = new HashSet<>();
}
