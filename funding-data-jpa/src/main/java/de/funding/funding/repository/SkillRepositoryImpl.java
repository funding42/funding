package de.funding.funding.repository;

import de.funding.funding.converter.PersistentSkillToSkillConverter;
import de.funding.funding.converter.SkillToPersistentSkillConverter;
import de.funding.funding.core.repository.SkillRepository;
import de.funding.funding.entity.PersistentSkill;
import de.funding.funding.entity.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SkillRepositoryImpl implements SkillRepository {

  @Autowired
  private JpaSkillRepository delegate;

  @Autowired
  private PersistentSkillToSkillConverter persistentSkillToSkillConverter;

  @Autowired
  private SkillToPersistentSkillConverter skillToPersistentSkillConverter;

  @Override
  public List<Skill> findAll() {
    return delegate.findAll().stream().map(persistentSkillToSkillConverter::convert)
        .collect(Collectors.toList());
  }

  @Override
  public void add(final Skill skill) {
    final PersistentSkill entity = skillToPersistentSkillConverter.convert(skill);
    delegate.save(entity);
  }
}
