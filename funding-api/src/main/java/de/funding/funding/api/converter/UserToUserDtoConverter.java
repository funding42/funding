package de.funding.funding.api.converter;

import de.funding.funding.api.dto.UserDto;
import de.funding.funding.entity.Skill;
import de.funding.funding.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;

import java.util.ArrayList;
import java.util.List;

public class UserToUserDtoConverter implements Converter<User, UserDto> {

  private final DefaultConversionService defaultConversionService;

  @Autowired
  public UserToUserDtoConverter(final DefaultConversionService defaultConversionService) {
    this.defaultConversionService = defaultConversionService;
  }

  @Override
  public UserDto convert(final User user) {
    return new UserDto(
            user.getUuid(),
            user.getPrename(),
            user.getSurname(),
            defaultConversionService.convert(user.getType(), UserDto.Type.class),
            convert(user.getSkills()),
            user.getDescription(),
            null);
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
