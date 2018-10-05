package de.funding.funding.api.converter;

import de.funding.funding.api.dto.ProjectDto;
import de.funding.funding.api.dto.ProjectListDto;
import de.funding.funding.api.dto.SlotDto;
import de.funding.funding.api.dto.SupporterDto;
import de.funding.funding.api.dto.UserDto;
import de.funding.funding.api.dto.UserOverviewDto;
import de.funding.funding.entity.Project;
import de.funding.funding.entity.Slot;
import de.funding.funding.entity.Supporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class ProjectToProjectListDtoConverter implements Converter<Project, ProjectListDto> {

  private final DefaultConversionService defaultConversionService;

  @Autowired
  public ProjectToProjectListDtoConverter(final DefaultConversionService defaultConversionService) {
    this.defaultConversionService = defaultConversionService;
  }

  @Override
  public ProjectListDto convert(final Project project) {

    Set<UUID> supportersInSlots = new HashSet<>();
    List<SlotDto> slots = new ArrayList<>();
    for(Slot slot: project.getSlots()) {
      slots.add(defaultConversionService.convert(slot, SlotDto.class));
      if(slot.getSupporter() != null) {
        supportersInSlots.add(slot.getSupporter().getUuid());
      }
    }

    List<SupporterDto> supporters = new ArrayList<>();
    if(project.getSupporters() != null) {
      for(Supporter supporter: project.getSupporters()) {
        if(!supportersInSlots.contains(supporter.getUuid())) {
          supporters.add(defaultConversionService.convert(supporter, SupporterDto.class));
        }
      }
    }

    return new ProjectListDto(
            project.getUuid(),
            project.getTitle(),
            project.getTeaser(),
            defaultConversionService.convert(project.getState(), ProjectDto.State.class),
            defaultConversionService.convert(project.getCreator(), UserOverviewDto.class),
            project.getCreatedAt(),
            project.getLastModified(),
            project.getSupporters() != null? project.getSupporters().size():0,
            0.0,
            0.0,
            0.0,
            null);


  }

}
