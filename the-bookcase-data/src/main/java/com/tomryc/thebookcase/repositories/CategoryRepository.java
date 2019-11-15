package com.tomryc.thebookcase.repositories;

import com.tomryc.thebookcase.model.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

Optional<Category> findByDescription(String description);
}
