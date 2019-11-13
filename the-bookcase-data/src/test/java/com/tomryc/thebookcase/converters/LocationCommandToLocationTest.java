package com.tomryc.thebookcase.converters;

import com.tomryc.thebookcase.commands.LocationCommand;
import com.tomryc.thebookcase.model.Location;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LocationCommandToLocationTest {

    private LocationCommandToLocation converter;
    private static final Long LONG_VALUE = 1L;
    private static final String DESCRIPTION = "description";

    @BeforeEach
    public void setUp(){
        converter = new LocationCommandToLocation();
    }

    @AfterEach
    public void tearDown(){
        converter = null;
    }

    @Test
    public void testNullParameter(){
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject(){
        assertNotNull(converter.convert(new LocationCommand()));
    }

    @Test
    public void convert(){
        //given
        LocationCommand command = new LocationCommand();
        command.setId(LONG_VALUE);
        command.setDescription(DESCRIPTION);

        //when
        Location location = converter.convert(command);

        //then
        assertNotNull(location);
        assertEquals(LONG_VALUE, location.getId());
        assertEquals(DESCRIPTION, location.getDescription());
    }

}