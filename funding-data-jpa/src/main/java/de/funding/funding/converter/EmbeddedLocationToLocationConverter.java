package de.funding.funding.converter;

import de.funding.funding.entity.EmbeddableLocation;
import de.funding.funding.entity.Location;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EmbeddedLocationToLocationConverter
    implements Converter<EmbeddableLocation, Location> {

  @Override
  public Location convert(final EmbeddableLocation source) {
    return new Location(source.getLatitude(), source.getLongitude());
  }
}
