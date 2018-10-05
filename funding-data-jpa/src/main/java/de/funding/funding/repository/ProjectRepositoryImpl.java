package de.funding.funding.repository;

import de.funding.funding.converter.PersistentProjectToProjectConverter;
import de.funding.funding.core.repository.ProjectRepository;
import de.funding.funding.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProjectRepositoryImpl implements ProjectRepository {

  @Autowired
  private JpaProjectRepository delegator;

  @Autowired
  private PersistentProjectToProjectConverter persistentProjectToProjectConverter;

  @Override
  public Project getProject(final UUID uuid) {
    return delegator.findById(uuid).map(persistentProjectToProjectConverter::convert).orElse(null);
  }
}
