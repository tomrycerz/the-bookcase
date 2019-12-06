package com.tomryc.thebookcase.services;


import com.tomryc.thebookcase.commands.LocationCommand;
import com.tomryc.thebookcase.converters.LocationCommandToLocation;
import com.tomryc.thebookcase.converters.LocationToLocationCommand;
import com.tomryc.thebookcase.exceptions.NotFoundException;
import com.tomryc.thebookcase.model.Location;
import com.tomryc.thebookcase.repositories.LocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class LocationServiceImplTest {

    LocationToLocationCommand locationToLocationCommand = new LocationToLocationCommand();
    LocationService locationService;

    @Mock
    LocationRepository locationRepository;

    @Mock
    LocationCommandToLocation locationCommandToLocation;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        locationService = new LocationServiceImpl(locationRepository, locationToLocationCommand, locationCommandToLocation);
    }

    @Test
    public void listAllCategories() throws Exception {
        //given
        Set<Location> locations = new HashSet<>();
        Location location1 = new Location();
        location1.setId(1L);
        locations.add(location1);

        Location location2 = new Location();
        location2.setId(2L);
        locations.add(location2);

        when(locationRepository.findAll()).thenReturn(locations);

        //when
        Set<LocationCommand> commands = locationService.listAllLocations();

        //then
        assertEquals(2, commands.size());
        verify(locationRepository, times(1)).findAll();
    }

    @Test
    public void getLocationByIdNotFoundTest() throws Exception{

        Optional<Location> authorOptional = Optional.empty();

        when(locationRepository.findById(anyLong())).thenReturn(authorOptional);

        assertThrows(NotFoundException.class, () -> locationService.findById(1l));
    }
}
