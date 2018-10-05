package de.funding.funding.core.repository;

import java.util.UUID;

import de.funding.funding.entity.Project;

public interface ProjectRepository {

	void create(Project project);

	Project getProject(UUID uuid);

}
