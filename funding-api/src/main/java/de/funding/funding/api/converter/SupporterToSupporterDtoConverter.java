package de.funding.funding.api.converter;

import de.funding.funding.api.dto.SupporterDto;
import de.funding.funding.api.dto.UserOverviewDto;
import de.funding.funding.entity.Skill;
import de.funding.funding.entity.Supporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;

import java.util.ArrayList;
import java.util.List;

public class SupporterToSupporterDtoConverter implements Converter<Supporter, SupporterDto>{

  private final DefaultConversionService defaultConversionService;

  @Autowired
  public SupporterToSupporterDtoConverter(final DefaultConversionService defaultConversionService) {
    this.defaultConversionService = defaultConversionService;
  }

  @Override
  public SupporterDto convert(final Supporter supporter) {
    return new SupporterDto(
            defaultConversionService.convert(supporter.getUser(), UserOverviewDto.class),
            convert(supporter.getSkills()),
            supporter.getAmountInvested()
    );
  }

  protected List<String> convert(List<Skill> skills) {
    List<String> result = new ArrayList<>();
    if(skills != null) {
      for(Skill skill: skills) {
        result.add(skill.getName());
      }
    }
    return result;
  }
}
