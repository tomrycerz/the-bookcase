package com.tomryc.thebookcase.commands;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class AuthorCommand {

    private Long id;
    private String name;
}
