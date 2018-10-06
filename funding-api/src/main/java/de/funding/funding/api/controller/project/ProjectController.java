package de.funding.funding.api.controller.project;

import de.funding.funding.api.dto.ProjectDto;
import de.funding.funding.api.dto.ProjectListDto;
import de.funding.funding.core.repository.ProjectRepository;
import de.funding.funding.core.service.VoteService;
import de.funding.funding.entity.Image;
import de.funding.funding.entity.Project;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
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
  public List<ProjectListDto> getProjects(HttpServletRequest request, final Long offset, final Long limit, final SortBy sortBy,
                                          final Boolean asc) {

    ProjectRepository.SortBy convertedSortBy = convert(sortBy);

    final List<Project> projects = projectRepository.getProjects(offset, limit, convertedSortBy, asc);
    List<ProjectListDto> result = new ArrayList<>();
    for(Project project: projects) {
      result.add(convertToListDto(project, request));
    }
    return result;
  }

  @GetMapping("/{uuid}")
  public ProjectDto getProject(HttpServletRequest request, @PathVariable("uuid") final UUID uuid) {

    final Project project = projectRepository.getProject(uuid);

    if (project == null) {
      throw new ProjectNotFoundException(uuid);
    }

    return convertToProjectDto(project, request);
  }

  @GetMapping("/image/{uuid}")
  public void getProjectImage(@PathVariable("uuid") final UUID uuid, HttpServletResponse response) throws IOException {

    final Project project = projectRepository.getProject(uuid);

    if (project == null) {
      throw new ProjectNotFoundException(uuid);
    }

    final Image projectImage = projectRepository.getProjectImage(uuid);

    if(projectImage == null) {
      throw new ProjectNotFoundException(uuid);
    }

    response.setContentType(projectImage.getMimeType());
    IOUtils.copy(projectImage.getStream(), response.getOutputStream());
  }

  @GetMapping("/search")
  public List<ProjectListDto> searchByTitle(HttpServletRequest request, final String title, final Long offset, final Long limit,
                                            final SortBy sortBy, final Boolean asc) {

    ProjectRepository.SortBy convertedSortBy = convert(sortBy);

    final List<Project> projects = projectRepository.searchByTitle(title, offset, limit, convertedSortBy, asc);
    List<ProjectListDto> result = new ArrayList<>();
    for(Project project: projects) {
      result.add(convertToListDto(project, request));
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

  private ProjectListDto convertToListDto(Project project, HttpServletRequest request) {
    final ProjectListDto projectDto = defaultConversionService.convert(project, ProjectListDto.class);
    VoteService.VoteResult voteResult = voteService.calculateVoteResult(project);

    projectDto.setVotes(voteResult.getVotes());
    projectDto.setPercentUpvotes(voteResult.getPercentUpvotes());
    projectDto.setTeaserPictureUrl(getImageUri(project.getUuid(), request));

    return projectDto;
  }

  private ProjectDto convertToProjectDto(Project project, HttpServletRequest request) {
    final ProjectDto projectDto = defaultConversionService.convert(project, ProjectDto.class);
    VoteService.VoteResult voteResult = voteService.calculateVoteResult(project);

    projectDto.setVotes(voteResult.getVotes());
    projectDto.setPercentUpvotes(voteResult.getPercentUpvotes());
    projectDto.setTeaserPictureUrl(getImageUri(project.getUuid(), request));
    if (project.getSupporters() != null) {
      final double sum = project.getSupporters().stream().mapToDouble(p -> p.getAmountInvested() != null ? p.getAmountInvested() : 0.0).sum();
      projectDto.setInvestmentProgress(sum);
    }

    return projectDto;
  }

  private String getImageUri(UUID projectUuid, HttpServletRequest request) {
    final String path = request.getRequestURL().toString().replace(request.getRequestURI(), request.getContextPath());
    return path + "/api/project/image/" + projectUuid;
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  public static class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException(UUID uuid) {
      super("Project not found: " + uuid);
    }
  }
}
