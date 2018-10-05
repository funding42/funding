package de.funding.funding.converter;

import de.funding.funding.entity.PersistentUser;
import de.funding.funding.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserToPersistentConverter implements Converter<User, PersistentUser> {

  @Autowired
  private SkillToPersistentSkillConverter skillToPersistentSkillConverter;

  @Override
  public PersistentUser convert(final User source) {
    final PersistentUser target = new PersistentUser();
    target.setDescription(source.getDescription());
    target.setFirstName(source.getPrename());
    target.setLastName(source.getSurname());
    target.setId(source.getUuid());
    target.setType(source.getType());
    target.setSkills(source.getSkills().stream().map(skillToPersistentSkillConverter::convert)
        .collect(Collectors.toSet()));
    return target;
  }
}
