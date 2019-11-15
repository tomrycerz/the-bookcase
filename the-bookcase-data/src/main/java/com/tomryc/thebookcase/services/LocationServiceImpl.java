package com.tomryc.thebookcase.services;

import com.tomryc.thebookcase.commands.LocationCommand;
import com.tomryc.thebookcase.converters.LocationToLocationCommand;
import com.tomryc.thebookcase.repositories.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class LocationServiceImpl implements LocationService{

    private final LocationRepository locationRepository;
    private final LocationToLocationCommand locationToLocationCommand;

    public LocationServiceImpl(LocationRepository locationRepository, LocationToLocationCommand locationToLocationCommand) {
        this.locationRepository = locationRepository;
        this.locationToLocationCommand = locationToLocationCommand;
    }

    @Override
    public Set<LocationCommand> listAllLocations() {
        return StreamSupport.stream(locationRepository.findAll()
                .spliterator(), false)
                .map(locationToLocationCommand::convert)
                .collect(Collectors.toSet());
    }
}
