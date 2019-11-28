package com.tomryc.thebookcase.services;

import com.tomryc.thebookcase.commands.LocationCommand;
import com.tomryc.thebookcase.model.Location;

import java.util.Set;

public interface LocationService {

    Set<LocationCommand> listAllLocations();

    Location findById(Long id);

    LocationCommand findCommandById(Long l);

    LocationCommand saveLocationCommand(LocationCommand command);

    void deleteById(Long idToDelete);
}
