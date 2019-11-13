package com.tomryc.thebookcase.converters;

import com.tomryc.thebookcase.commands.LocationCommand;
import com.tomryc.thebookcase.model.Location;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LocationToLocationCommandTest {

    private static final Long LONG_VALUE = 1L;
    private static final String DESCRIPTION = "description";
    private LocationToLocationCommand converter;

    @BeforeEach
    public void setUp(){
        converter = new LocationToLocationCommand();
    }

    @AfterEach
    public void tearDown(){
        converter =  null;
    }

    @Test
    public void testNullParameter(){
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject(){
       assertNotNull(converter.convert(new Location()));
    }

    @Test
    public void convert(){
        //given
        Location location = new Location();
        location.setId(LONG_VALUE);
        location.setDescription(DESCRIPTION);

        //when
        LocationCommand command = converter.convert(location);

        //then
        assertNotNull(command);
        assertEquals(LONG_VALUE, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
    }

}