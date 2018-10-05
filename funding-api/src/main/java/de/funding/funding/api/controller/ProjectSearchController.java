package de.funding.funding.api.controller;

import de.funding.funding.api.dto.ProjectListDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/api/v1/search/projects")
public class ProjectSearchController {

  public enum SortBy {CreationDate, ModifiedDate, Trending, Popular}

  @GetMapping("/api/v1/search/projects")
  public List<ProjectListDto> searchByTitle(String title, Long offset, Long limit, SortBy sortBy, Boolean asc) {
    throw new UnsupportedOperationException();
  }
}
