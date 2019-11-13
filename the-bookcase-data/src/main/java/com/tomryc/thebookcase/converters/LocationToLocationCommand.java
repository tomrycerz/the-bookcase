package com.tomryc.thebookcase.converters;

import com.tomryc.thebookcase.commands.LocationCommand;
import com.tomryc.thebookcase.model.Location;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class LocationToLocationCommand implements Converter<Location, LocationCommand> {

    @Synchronized
    @Nullable
    @Override
    public LocationCommand convert(Location source){
        if(source == null){
            return null;
        }
        final LocationCommand locationCommand = new LocationCommand();

        locationCommand.setId(source.getId());
        locationCommand.setDescription(source.getDescription());

        return locationCommand;
    }
}
