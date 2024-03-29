package com.tomryc.thebookcase.repositories;

import com.tomryc.thebookcase.model.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Author, Long> {

    Optional<Author> findByName(String name);

    List<Author> findAllByNameLike(String name);
}
