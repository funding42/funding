package de.funding.funding.repository;

import de.funding.funding.converter.PersistentProjectToProjectConverter;
import de.funding.funding.converter.ProjectToPersistentProjectConverter;
import de.funding.funding.core.repository.ProjectRepository;
import de.funding.funding.entity.Image;
import de.funding.funding.entity.PersistentProject;
import de.funding.funding.entity.Project;
import de.funding.funding.util.OffsetBasedPageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

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
    final PersistentProject persistentProject =
            projectToPersistentProjectConverter.convert(project);
    delegator.save(persistentProject);
  }

  @Override
  public Project getProject(final UUID uuid) {
    return delegator.findById(uuid).map(persistentProjectToProjectConverter::convert).orElse(null);
  }

  @Override
  public List<Project> getProjects(final Long offset, final Long limit, final SortBy sortBy,
                                   final Boolean asc) {

    Pageable pageable = getPageable(offset, limit, sortBy, asc);

    return delegator.findAll(pageable).map(persistentProjectToProjectConverter::convert)
            .getContent();
  }

  @Override
  public List<Project> searchByTitle(final String title, final Long offset, final Long limit,
                                     final SortBy sortBy, final Boolean asc) {
    Pageable pageable = getPageable(offset, limit, sortBy, asc);

    String titleSearch = "%" + title + "%";

    return delegator.findAllByTitleLike(titleSearch, pageable)
            .map(persistentProjectToProjectConverter::convert).getContent();
  }

  @Override
  public Image getProjectImage(final UUID projectUUid) {
    final PersistentProject persistentProject = delegator
            .findById(projectUUid).orElse(null);
    if (persistentProject != null && persistentProject.getImage() != null && persistentProject.getImageMimetype() != null) {
      return new ProjectImage(persistentProject);
    } else {
      return null;
    }
  }

  @Override
  public void cachePopularity(final Project projectUuid, final double popularity) {
    delegator.findById(projectUuid.getUuid()).ifPresent(p -> {
      p.setPopularity(popularity);
      delegator.saveAndFlush(p);
    });
  }

  @Override
  public void cacheTrendingScore(final Project projectUuid, final double trendingScore) {
    delegator.findById(projectUuid.getUuid()).ifPresent(p -> {
      p.setTrendingScore(trendingScore);
      delegator.saveAndFlush(p);
    });
  }

  private Pageable getPageable(Long offset, Long limit, final SortBy sortBy, final Boolean asc) {

    offset = offset != null ? offset : 0;
    limit = limit != null ? limit : 100;

    Pageable pageable;
    if (sortBy != null) {
      String fieldName = null;
      switch (sortBy) {
        case CreationDate:
          fieldName = "creationDate";
          break;
        case ModifiedDate:
          fieldName = "lastModified";
          break;
        case Popular:
          fieldName = "popularity";
          break;
        case Trending:
          fieldName = "trendingScore";
          break;
        default:
          throw new UnsupportedOperationException("unknown sortby: " + sortBy);
      }
      Sort sort =
              new Sort(asc == null || asc ? Sort.Direction.ASC : Sort.Direction.DESC, fieldName);
      pageable = new OffsetBasedPageRequest(offset, limit.intValue(), sort);
    } else {
      pageable = new OffsetBasedPageRequest(offset, limit.intValue());
    }

    return pageable;
  }

  private static class ProjectImage implements Image {

    private final PersistentProject persistentProject;

    public ProjectImage(final PersistentProject persistentProject) {
      this.persistentProject = persistentProject;
    }

    @Override
    public InputStream getStream() throws IOException {
      try {
        return persistentProject.getImage() != null?persistentProject.getImage().getBinaryStream():null;
      } catch (SQLException e) {
        throw new IOException(e);
      }
    }

    @Override
    public String getMimeType() {
      return persistentProject.getImageMimetype();
    }
  }

}
