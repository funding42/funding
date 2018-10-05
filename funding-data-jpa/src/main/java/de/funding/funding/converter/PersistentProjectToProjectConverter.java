package de.funding.funding.converter;

import de.funding.funding.entity.Location;
import de.funding.funding.entity.PersistentProject;
import de.funding.funding.entity.Project;
import de.funding.funding.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class PersistentProjectToProjectConverter implements Converter<PersistentProject, Project> {

  @Autowired
  private PersistentUserToUserConverter userConverter;

  @Autowired
  private EmbeddedLocationToLocationConverter locationConverter;

  @Override
  public Project convert(final PersistentProject source) {
    final User creator =
        Optional.ofNullable(source.getCreator()).map(userConverter::convert).orElse(null);
    final Location location =
        Optional.ofNullable(source.getLocation()).map(locationConverter::convert).orElse(null);
    return new Project(source.getId(), source.getTitle(), source.getTeaser(),
        source.getDescription(), source.getState(), creator, source.getCreatedAt(),
        source.getUpdatedAt(), location, source.getFundingGoal(), new ArrayList<>());
  }
}
