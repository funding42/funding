package de.funding.funding.boot;

import java.util.List;
import java.util.UUID;

import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import de.funding.funding.core.boot.DataProvider;
import de.funding.funding.core.repository.UserRepository;
import de.funding.funding.entity.Skill;
import de.funding.funding.entity.User;
import de.funding.funding.entity.UserType;

@Service
public class DemoDataProvider implements DataProvider {

	private static final int USER_COUNT = 15;
	private static final int PROJECT_COUNT = 30;

	@Autowired
	private UserRepository userRepo;

	@Override
	public void load() {
		initializeUserData();
		initializeProjectData();
	}

	private void initializeProjectData() {
		for (int i = 0; i < PROJECT_COUNT; i++) {

		}
	}

	private void initializeUserData() {
		final DataFactory dataFactory = new DataFactory();
		List<Skill> skills = null;
		for (int i = 0; i < USER_COUNT / 3; i++) {
			skills = createRandomSkillList();
			final User user = new User(UUID.randomUUID(), dataFactory.getFirstName(), dataFactory.getLastName(),
					UserType.Citizen, skills, dataFactory.getRandomText(250));
			userRepo.createUser(user);
		}
		for (int i = 0; i < USER_COUNT / 3; i++) {
			skills = createRandomSkillList();
			final User user = new User(UUID.randomUUID(), dataFactory.getFirstName(), dataFactory.getLastName(),
					UserType.Commercial, skills, dataFactory.getRandomText(250));
			userRepo.createUser(user);
		}
		for (int i = 0; i < USER_COUNT / 3; i++) {
			skills = createRandomSkillList();
			final User user = new User(UUID.randomUUID(), dataFactory.getFirstName(), dataFactory.getLastName(),
					UserType.PublicInstitution, skills, dataFactory.getRandomText(250));
			userRepo.createUser(user);
		}
	}

	private List<Skill> createRandomSkillList() {
		final List<Skill> skills = Lists.newArrayList();

		// TODO SkillRepo

		return skills;
	}
}
