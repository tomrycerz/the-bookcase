package com.tomryc.thebookcase.controllers;

import com.tomryc.thebookcase.commands.LocationCommand;
import com.tomryc.thebookcase.exceptions.NotFoundException;
import com.tomryc.thebookcase.model.Location;
import com.tomryc.thebookcase.services.LocationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class LocationControllerTest {

    @Mock
    LocationService locationService;

    LocationController controller;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new LocationController(locationService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    public void testGetLocation() throws Exception {

        Location location = new Location();
        location.setId(1L);

        when(locationService.findById(anyLong())).thenReturn(location);

        mockMvc.perform(get("/locations/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("location/show"))
                .andExpect(model().attributeExists("location"));
    }

    @Test
    public void testGetLocationNotFound() throws Exception {

        when(locationService.findById(anyLong())).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/locations/1/show"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("404error"));
    }

    @Test
    public void testGetLocationNumberFormatException() throws Exception {

        mockMvc.perform(get("/locations/asdf/show"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("400error"));
    }

    @Test
    public void testGetNewLocationForm() throws Exception {
        LocationCommand command = new LocationCommand();

        mockMvc.perform(get("/locations/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("location/locationform"))
                .andExpect(model().attributeExists("location"));
    }

    @Test
    public void testPostNewLocationForm() throws Exception {
        LocationCommand command = new LocationCommand();
        command.setId(2L);

        when(locationService.saveLocationCommand(any())).thenReturn(command);

        mockMvc.perform(post("/location")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "some string")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/locations/2/show"));
    }

/*    @Test
    public void testPostNewLocationFormValidationFail() throws Exception{

        LocationCommand command = new LocationCommand();
        command.setId(2L);

        when(locationService.saveLocationCommand(any())).thenReturn(command);

        mockMvc.perform(post("/locations")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("name", "Some Name")
        )
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("location"))
                .andExpect(view().name("location/locationform"));

    }*/


    @Test
    public void testGetUpdateView() throws Exception {
        LocationCommand command = new LocationCommand();
        command.setId(2L);

        when(locationService.findCommandById(anyLong())).thenReturn(command);

        mockMvc.perform(get("/locations/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("location/locationform"))
                .andExpect(model().attributeExists("location"));
    }

    @Test
    public void testDeleteAction() throws Exception {
        mockMvc.perform(get("/locations/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        verify(locationService, times(1)).deleteById(anyLong());
    }


}
