package de.funding.funding.converter;

import de.funding.funding.entity.PersistentSkill;
import de.funding.funding.entity.Skill;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PersistentSkillToSkillConverter implements Converter<PersistentSkill, Skill> {

  @Override
  public Skill convert(final PersistentSkill source) {
    return new Skill(source.getId(), source.getName(), source.getDescription());
  }
}
