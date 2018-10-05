package de.funding.funding.converter;

import de.funding.funding.entity.PersistentUser;
import de.funding.funding.entity.Skill;
import de.funding.funding.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersistentUserToUserConverter implements Converter<PersistentUser, User> {

  @Autowired
  private PersistentSkillToSkillConverter persistentSkillToSkillConverter;

  @Override
  public User convert(final PersistentUser source) {
    final List<Skill> skills =
        source.getSkills().stream().map(persistentSkillToSkillConverter::convert)
            .collect(Collectors.toList());
    return new User(source.getId(), source.getFirstName(), source.getLastName(), source.getType(),
        skills, source.getDescription());
  }
}
