package com.tomryc.thebookcase.services;

import com.tomryc.thebookcase.commands.LocationCommand;

import java.util.Set;

public interface LocationService {

    Set<LocationCommand> listAllLocations();
}
