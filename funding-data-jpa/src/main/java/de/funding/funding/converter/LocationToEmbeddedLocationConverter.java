package de.funding.funding.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import de.funding.funding.entity.EmbeddableLocation;
import de.funding.funding.entity.Location;

@Component
public class LocationToEmbeddedLocationConverter implements Converter<Location, EmbeddableLocation> {

	@Override
	public EmbeddableLocation convert(final Location source) {
		final EmbeddableLocation target = new EmbeddableLocation();
		if(source != null) {
			target.setLatitude(source.getBreitengrad());
			target.setLongitude(source.getLaengengrad());
		}

		return target;
	}

}
