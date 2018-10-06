package de.funding.funding.boot;

import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;
import com.google.common.collect.Lists;

import de.funding.funding.core.boot.DataProvider;
import de.funding.funding.core.repository.ProjectRepository;
import de.funding.funding.core.repository.SkillRepository;
import de.funding.funding.core.repository.SlotRepository;
import de.funding.funding.core.repository.SupporterRepository;
import de.funding.funding.core.repository.UserRepository;
import de.funding.funding.entity.ProjectState;
import de.funding.funding.entity.Skill;
import de.funding.funding.entity.User;
import de.funding.funding.entity.UserType;

@Service
public class DemoDataProvider implements DataProvider {

	private static final Integer SKILL_COUNT = 10;
	private static final Integer USER_COUNT = 50;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ProjectRepository projectRepo;

	@Autowired
	private SkillRepository skillRepo;

	@Autowired
	private SlotRepository slotRepo;

	@Autowired
	private SupporterRepository supporterRepo;

	private ProjectState getRandomProjectState() {
		final Random random = new Random();
		return ProjectState.values()[random.nextInt(ProjectState.values().length)];
	}

	private List<Skill> getRandomSkills() {
		final List<Skill> allSkills = skillRepo.findAll();
		final Random random = new Random();
		final List<Skill> randomSkills = Lists.newArrayList();
		for (int i = 0; i < random.nextInt(5); i++) {
			randomSkills.add(allSkills.get(random.nextInt(allSkills.size())));
		}
		return randomSkills;
	}

	private User getRandomUser() {
		final Random random = new Random();
		final Integer randomOffset = random.nextInt(USER_COUNT);
		return userRepo.getUsers(randomOffset.longValue(), USER_COUNT.longValue()).get(0);
	}

	private UserType getRandomUserType() {
		final Random random = new Random();
		return UserType.values()[random.nextInt(UserType.values().length)];
	}

	private void initializeSkillsData() {
		final Faker faker = new Faker(Locale.GERMAN);
		for (int i = 0; i < SKILL_COUNT; i++) {
			skillRepo.add(new Skill(UUID.randomUUID(), faker.beer().name(), faker.chuckNorris().fact()));
		}
	}

	private void initializeUserData() {
		final Faker faker = new Faker(Locale.GERMAN);
		for (int i = 0; i < USER_COUNT; i++) {
			final List<Skill> skills = getRandomSkills();
			final User user = new User(UUID.randomUUID(), faker.name().firstName(), faker.name().lastName(),
					getRandomUserType(), skills, faker.chuckNorris().fact());
			userRepo.createUser(user);
		}
	}

	@Override
	public void load() {
		initializeSkillsData();
		initializeUserData();
	}
}
