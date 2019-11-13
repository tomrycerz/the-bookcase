package com.tomryc.thebookcase.converters;

import com.tomryc.thebookcase.commands.LocationCommand;
import com.tomryc.thebookcase.model.Location;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class LocationCommandToLocation  implements Converter<LocationCommand, Location> {


    @Synchronized
    @Nullable
    @Override
    public Location convert(LocationCommand source){
        if(source == null){
          return null;
        }
        final Location location = new Location();

        location.setId(source.getId());
        location.setDescription(source.getDescription());

        return location;
    }
}
