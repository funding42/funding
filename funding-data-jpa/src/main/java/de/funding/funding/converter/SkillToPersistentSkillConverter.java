package de.funding.funding.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import de.funding.funding.entity.PersistentSkill;
import de.funding.funding.entity.Skill;

@Component
public class SkillToPersistentSkillConverter implements Converter<Skill, PersistentSkill> {

	@Override
	public PersistentSkill convert(final Skill source) {
		final PersistentSkill target = new PersistentSkill();
		target.setDescription(source.getDescription());
		target.setId(source.getUuid());
		target.setName(source.getName());

		return target;
	}

}
