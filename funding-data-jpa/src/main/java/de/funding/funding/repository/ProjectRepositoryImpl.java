package de.funding.funding.repository;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.funding.funding.converter.PersistentProjectToProjectConverter;
import de.funding.funding.converter.ProjectToPersistentProjectConverter;
import de.funding.funding.core.repository.ProjectRepository;
import de.funding.funding.entity.PersistentProject;
import de.funding.funding.entity.Project;

@Component
public class ProjectRepositoryImpl implements ProjectRepository {

	@Autowired
	private JpaProjectRepository delegator;

	@Autowired
	private PersistentProjectToProjectConverter persistentProjectToProjectConverter;

	@Autowired
	private ProjectToPersistentProjectConverter projectToPersistentProjectConverter;

	@Override
	public void create(final Project project) {
		final PersistentProject persistentProject = projectToPersistentProjectConverter.convert(project);
		persistentProject.setCreatedAt(LocalDateTime.now());
		persistentProject.setUpdatedAt(LocalDateTime.now());
		delegator.saveAndFlush(persistentProject);
	}

	@Override
	public Project getProject(final UUID uuid) {
		return delegator.findById(uuid).map(persistentProjectToProjectConverter::convert).orElse(null);
	}

}
