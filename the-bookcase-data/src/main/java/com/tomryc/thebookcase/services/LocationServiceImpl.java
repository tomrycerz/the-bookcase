package com.tomryc.thebookcase.services;

import com.tomryc.thebookcase.commands.LocationCommand;
import com.tomryc.thebookcase.converters.LocationCommandToLocation;
import com.tomryc.thebookcase.converters.LocationToLocationCommand;
import com.tomryc.thebookcase.exceptions.NotFoundException;
import com.tomryc.thebookcase.model.Location;
import com.tomryc.thebookcase.repositories.LocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class LocationServiceImpl implements LocationService{

    private final LocationRepository locationRepository;
    private final LocationToLocationCommand locationToLocationCommand;
    private final LocationCommandToLocation locationCommandToLocation;

    public LocationServiceImpl(LocationRepository locationRepository, LocationToLocationCommand locationToLocationCommand, LocationCommandToLocation locationCommandToLocation) {
        this.locationRepository = locationRepository;
        this.locationToLocationCommand = locationToLocationCommand;
        this.locationCommandToLocation = locationCommandToLocation;
    }

    @Override
    public Set<LocationCommand> listAllLocations() {
        return StreamSupport.stream(locationRepository.findAll()
                .spliterator(), false)
                .map(locationToLocationCommand::convert)
                .collect(Collectors.toSet());
    }

    @Override
    public Location findById(Long id){

        Optional<Location> locationOptional = locationRepository.findById(id);

        if(!locationOptional.isPresent()){
            throw new NotFoundException("Author not found. For ID value: " + id.toString());
        }
        return locationOptional.get();
    }

    @Override
    @Transactional
    public LocationCommand findCommandById(Long l) {
        return locationToLocationCommand.convert(findById(l));
    }

    @Override
    @Transactional
    public LocationCommand saveLocationCommand(LocationCommand command) {

        Location detachedLocation = locationCommandToLocation.convert(command);
        Location savedLocation = locationRepository.save(detachedLocation);
        log.debug("Saved Location Id: " + savedLocation.getId());

        return locationToLocationCommand.convert(savedLocation);
    }

    @Override
    public void deleteById(Long idToDelete) {
        locationRepository.deleteById(idToDelete);
    }
}
