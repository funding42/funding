package de.funding.funding.api.converter;

import de.funding.funding.api.dto.ProjectDto;
import de.funding.funding.entity.ProjectState;
import org.springframework.core.convert.converter.Converter;

public class ProjectStateToStateDtoConverter implements Converter<ProjectState, ProjectDto.State> {
  @Override
  public ProjectDto.State convert(final ProjectState projectState) {
    if(projectState == null) return null;
    switch(projectState) {
      case New: return ProjectDto.State.New;
      case Accepted: return ProjectDto.State.Accepted;
      case Validated: return ProjectDto.State.Validated;
      default: throw new IllegalArgumentException("Unknown Type: " + projectState);
    }
  }
}
