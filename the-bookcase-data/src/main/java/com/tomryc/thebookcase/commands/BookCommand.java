package com.tomryc.thebookcase.commands;

import com.tomryc.thebookcase.model.Location;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class BookCommand {

    private Long id;
    private String title;
    private String isbn;
    private String url;
    private CategoryCommand category = new CategoryCommand();
    private AuthorCommand author = new AuthorCommand();
    private LocationCommand location = new LocationCommand();

}
