package de.funding.funding.api.controller;

import de.funding.funding.api.dto.ProjectDto;
import de.funding.funding.api.dto.ProjectListDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController("/api/v1/project")
public class ProjectController {

  public enum SortBy {CreationDate, ModifiedDate, Trending, Popular}

  @GetMapping("/api/v1/project/{uuid}")
  public ProjectDto getProject(UUID uuid) {
    throw new UnsupportedOperationException();
  }

  @GetMapping("/api/v1/project")
  public List<ProjectListDto> getProjects(Long offset, Long limit, SortBy sortBy, Boolean asc) {
    throw new UnsupportedOperationException();
  }
}
