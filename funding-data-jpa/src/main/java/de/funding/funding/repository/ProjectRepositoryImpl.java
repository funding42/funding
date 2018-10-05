package de.funding.funding.repository;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import de.funding.funding.util.OffsetBasedPageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

  @Override
  public List<Project> getProjects(final Long offset, final Long limit, final SortBy sortBy, final Boolean asc) {

    Pageable pageable = getPageable(offset, limit, sortBy, asc);

    return delegator.findAll(pageable).map(persistentProjectToProjectConverter::convert).getContent();
  }

  @Override
  public URI getTeaserPictureUri(final UUID projectId) {
    return null;
  }

  @Override
  public List<Project> searchByTitle(final String title, final Long offset, final Long limit, final SortBy sortBy, final Boolean asc) {
    Pageable pageable = getPageable(offset, limit, sortBy, asc);

    String titleSearch = "%"+title+"%";

    return delegator.findAllByTitleLike(titleSearch, pageable).map(persistentProjectToProjectConverter::convert).getContent();
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
          throw new UnsupportedOperationException("not yet implemented");
        case Trending:
          throw new UnsupportedOperationException("not yet implemented");
        default:
          throw new UnsupportedOperationException("unknown sortby: " + sortBy);
      }
      Sort sort = new Sort(asc == null || asc ? Sort.Direction.ASC : Sort.Direction.DESC, fieldName);
      pageable = new OffsetBasedPageRequest(offset, limit.intValue(), sort);
    } else {
      pageable = new OffsetBasedPageRequest(offset, limit.intValue());
    }

    return pageable;
  }

}
