package de.funding.funding.core.repository;


import java.net.URI;
import java.util.List;
import java.util.UUID;

import de.funding.funding.entity.Project;

public interface ProjectRepository {

  public enum SortBy {CreationDate, ModifiedDate, Trending, Popular}

  void create(Project project);

  Project getProject(UUID uuid);

  List<Project> getProjects(final Long offset, final Long limit, final SortBy sortBy,
                            final Boolean asc);

  URI getTeaserPictureUri(UUID projectId);

  List<Project> searchByTitle(final String title, final Long offset, final Long limit,
                              final SortBy sortBy, final Boolean asc);
}
