package de.funding.funding.core.repository;

import de.funding.funding.entity.Project;

import java.util.UUID;

public interface ProjectRepository {
  Project getProject(UUID uuid);

}
