package de.funding.funding.core.repository;

import de.funding.funding.entity.Project;

import java.net.URI;
import java.util.List;
import java.util.UUID;

public interface ProjectRepository {

  public enum SortBy {CreationDate, ModifiedDate, Trending, Popular}

  Project getProject(UUID uuid);

  List<Project> getProjects(final Long offset, final Long limit, final SortBy sortBy,
                            final Boolean asc);

  URI getTeaserPictureUri(UUID projectId);

  List<Project> searchByTitle(final String title, final Long offset, final Long limit,
                              final SortBy sortBy, final Boolean asc);
}
