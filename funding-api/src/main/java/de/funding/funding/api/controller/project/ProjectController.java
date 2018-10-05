package de.funding.funding.api.controller.project;

import de.funding.funding.api.dto.ProjectDto;
import de.funding.funding.api.dto.ProjectListDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/project")
public class ProjectController {

  public enum SortBy {CreationDate, ModifiedDate, Trending, Popular}

  @GetMapping("/")
  public List<ProjectListDto> getProjects(final Long offset, final Long limit, final SortBy sortBy,
      final Boolean asc) {
    throw new UnsupportedOperationException();
  }

  @GetMapping("/{uuid}")
  public ProjectDto getProject(@PathVariable("uuid") final UUID uuid) {
    throw new UnsupportedOperationException();
  }

  @GetMapping("/search")
  public List<ProjectListDto> searchByTitle(final String title, final Long offset, final Long limit,
      final SortBy sortBy, final Boolean asc) {
    throw new UnsupportedOperationException();
  }
}
