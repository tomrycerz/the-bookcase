package com.tomryc.thebookcase.repositories;

import com.tomryc.thebookcase.model.Location;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LocationRepository extends CrudRepository<Location, Long> {

    Optional<Location> findByDescription(String description);
}
