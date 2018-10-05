package de.funding.funding.api.controller.project;

import de.funding.funding.api.dto.ProjectDto;
import de.funding.funding.api.dto.ProjectListDto;
import de.funding.funding.core.repository.ProjectRepository;
import de.funding.funding.core.service.VoteService;
import de.funding.funding.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

  public enum SortBy {CreationDate, ModifiedDate, Trending, Popular}

  private final ProjectRepository projectRepository;

  private final DefaultConversionService defaultConversionService;

  private final VoteService voteService;

  @Autowired
  public ProjectController(final ProjectRepository projectRepository, final DefaultConversionService defaultConversionService, final VoteService voteService) {
    this.projectRepository = projectRepository;
    this.defaultConversionService = defaultConversionService;
    this.voteService = voteService;
  }

  @GetMapping("")
  public List<ProjectListDto> getProjects(final Long offset, final Long limit, final SortBy sortBy,
                                          final Boolean asc) {

    ProjectRepository.SortBy convertedSortBy = convert(sortBy);

    final List<Project> projects = projectRepository.getProjects(offset, limit, convertedSortBy, asc);
    List<ProjectListDto> result = new ArrayList<>();
    for(Project project: projects) {
      result.add(convertToListDto(project));
    }
    return result;
  }

  @GetMapping("/{uuid}")
  public ProjectDto getProject(@PathVariable("uuid") final UUID uuid) {

    final Project project = projectRepository.getProject(uuid);

    if (project == null) {
      throw new ProjectNotFoundException(uuid);
    }

    return convertToProjectDto(project);
  }

  @GetMapping("/search")
  public List<ProjectListDto> searchByTitle(final String title, final Long offset, final Long limit,
                                            final SortBy sortBy, final Boolean asc) {

    ProjectRepository.SortBy convertedSortBy = convert(sortBy);

    final List<Project> projects = projectRepository.searchByTitle(title, offset, limit, convertedSortBy, asc);
    List<ProjectListDto> result = new ArrayList<>();
    for(Project project: projects) {
      result.add(convertToListDto(project));
    }
    return result;
  }

  private ProjectRepository.SortBy convert(SortBy sortBy) {
    if(sortBy != null) {
      switch (sortBy) {
        case Popular: return ProjectRepository.SortBy.Popular;
        case CreationDate: return ProjectRepository.SortBy.CreationDate;
        case ModifiedDate: return ProjectRepository.SortBy.ModifiedDate;
        case Trending: return ProjectRepository.SortBy.Trending;
        default: throw new IllegalArgumentException("Unknown Sort Method: " + sortBy);
      }
    }
    return null;
  }

  private ProjectListDto convertToListDto(Project project) {
    final ProjectListDto projectDto = defaultConversionService.convert(project, ProjectListDto.class);
    VoteService.VoteResult voteResult = voteService.calculateVoteResult(project);

    projectDto.setVotes(voteResult.getVotes());
    projectDto.setPercentUpvotes(voteResult.getPercentUpvotes());
    projectDto.setTeaserPictureUrl(projectRepository.getTeaserPictureUri(project.getUuid()).toString());

    return projectDto;
  }

  private ProjectDto convertToProjectDto(Project project) {
    final ProjectDto projectDto = defaultConversionService.convert(project, ProjectDto.class);
    VoteService.VoteResult voteResult = voteService.calculateVoteResult(project);

    projectDto.setVotes(voteResult.getVotes());
    projectDto.setPercentUpvotes(voteResult.getPercentUpvotes());
    projectDto.setTeaserPictureUrl(projectRepository.getTeaserPictureUri(project.getUuid()).toString());
    if (project.getSupporters() != null) {
      final double sum = project.getSupporters().stream().mapToDouble(p -> p.getAmountInvested() != null ? p.getAmountInvested() : 0.0).sum();
      projectDto.setInvestmentProgress(sum);
    }

    return projectDto;
  }

  public static class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException(UUID uuid) {
      super("Project not found: " + uuid);
    }
  }
}
