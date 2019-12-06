package com.tomryc.thebookcase.controllers;

import com.tomryc.thebookcase.commands.LocationCommand;
import com.tomryc.thebookcase.services.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("locations/{id}/show")
    public String showById(@PathVariable String id, Model model){
        model.addAttribute("location", locationService.findById(Long.valueOf(id)));
        return "location/show";
    }

    @GetMapping("locations/new")
    public String newLocation(Model model){
        model.addAttribute("location", new LocationCommand());

        return "location/locationform";
    }

    @GetMapping("locations/{id}/update")
    public String updateLocation(@PathVariable String id, Model model){
        model.addAttribute("location", locationService.findCommandById(Long.valueOf(id)));

        return "location/locationform";
    }

    @PostMapping("location")
    public String saveOrUpdate(@ModelAttribute("location") LocationCommand command){
        LocationCommand savedCommand = locationService.saveLocationCommand(command);

        return "redirect:/locations/" + savedCommand.getId() + "/show";
    }

    @GetMapping("locations/{id}/delete")
    public String deleteById(@PathVariable String id){
        locationService.deleteById(Long.valueOf(id));

        return "redirect:/";
    }
}
