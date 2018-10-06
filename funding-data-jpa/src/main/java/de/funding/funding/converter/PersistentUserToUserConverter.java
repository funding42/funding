package de.funding.funding.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import de.funding.funding.entity.PersistentUser;
import de.funding.funding.entity.Skill;
import de.funding.funding.entity.User;

@Component
public class PersistentUserToUserConverter implements Converter<PersistentUser, User> {

	@Autowired
	private PersistentSkillToSkillConverter persistentSkillToSkillConverter;

	@Override
	public User convert(final PersistentUser source) {
		List<Skill> skills = null;
		if (source.getSkills() != null) {
			skills =
					source.getSkills().stream().map(persistentSkillToSkillConverter::convert)
					.collect(Collectors.toList());
		}
		return new User(source.getId(), source.getFirstName(), source.getLastName(), source.getType(),
				skills, source.getDescription());
	}
}
