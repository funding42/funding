package de.funding.funding.api.controller.project;

import de.funding.funding.api.dto.ProjectDto;
import de.funding.funding.api.dto.ProjectListDto;
import de.funding.funding.api.dto.SupporterDto;
import de.funding.funding.api.dto.UserDto;
import de.funding.funding.api.dto.UserOverviewDto;
import de.funding.funding.entity.Skill;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

  public enum SortBy {CreationDate, ModifiedDate, Trending, Popular}

  @GetMapping("")
  public List<ProjectListDto> getProjects(final Long offset, final Long limit, final SortBy sortBy,
      final Boolean asc) {
    UserOverviewDto userOverviewDto = new UserOverviewDto(UUID.randomUUID(), "Max", "Mustermann", UserDto.Type.Citizen, null);
    ProjectListDto projectListDto1 = new ProjectListDto(UUID.randomUUID(), "Projekt 1", "Teaser für Projekt 1", ProjectListDto.State.New, userOverviewDto, LocalDateTime.now(), LocalDateTime.now(), 4, 100, 100, 0.7, null);
    ProjectListDto projectListDto2 = new ProjectListDto(UUID.randomUUID(), "Projekt 2", "Teaser für Projekt 2", ProjectListDto.State.Accepted, userOverviewDto, LocalDateTime.now(), LocalDateTime.now(), 4, 100, 100, 0.7, null);
    return Arrays.asList(
            projectListDto1,
            projectListDto2
    );
  }

  @GetMapping("/{uuid}")
  public ProjectDto getProject(@PathVariable("uuid") final UUID uuid) {
    UserOverviewDto userOverviewDto = new UserOverviewDto(UUID.randomUUID(), "Max", "Mustermann", UserDto.Type.Citizen, null);
    UserDto userDto = new UserDto(UUID.randomUUID(), "Vorname", "Nachname", UserDto.Type.Citizen, Arrays.asList("handarbeit"), "Meine Beschreibung", null);
    SupporterDto supporterDto = new SupporterDto(userOverviewDto, Arrays.asList("handarbeit"), 50.0, true);
    return new ProjectDto(UUID.randomUUID(), "Projekt 1", "Teaser für Projekt 1", "Beschreibung für Projekt 2", ProjectDto.State.New, userDto, LocalDateTime.now(), LocalDateTime.now(), Arrays.asList(supporterDto), 100.0, 0.7, 100.0, 50.0, null);
  }

  @GetMapping("/search")
  public List<ProjectListDto> searchByTitle(final String title, final Long offset, final Long limit,
      final SortBy sortBy, final Boolean asc) {
    return getProjects(offset, limit, sortBy, asc);
  }
}
