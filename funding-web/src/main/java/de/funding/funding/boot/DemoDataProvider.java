package de.funding.funding.boot;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import de.funding.funding.core.boot.DataProvider;
import de.funding.funding.core.repository.ProjectRepository;
import de.funding.funding.core.repository.SkillRepository;
import de.funding.funding.core.repository.UserRepository;
import de.funding.funding.entity.Location;
import de.funding.funding.entity.Project;
import de.funding.funding.entity.ProjectState;
import de.funding.funding.entity.Skill;
import de.funding.funding.entity.Slot;
import de.funding.funding.entity.Supporter;
import de.funding.funding.entity.User;
import de.funding.funding.entity.UserType;

@Service
public class DemoDataProvider implements DataProvider {

	private static final Integer SKILL_COUNT = 10;
	private static final Integer USER_COUNT = 15;
	private static final Integer PROJECT_COUNT = 30;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ProjectRepository projectRepo;

	@Autowired
	private SkillRepository skillRepo;

	@Override
	public void load() {
		initializeSkillsData();
		initializeUserData();
		// initializeProjectData();
	}

	private void initializeSkillsData() {
		final DataFactory dataFactory = new DataFactory();
		for (int i = 0; i < SKILL_COUNT; i++) {
			skillRepo.add(new Skill(UUID.randomUUID(), dataFactory.getCity(), dataFactory.getRandomWord()));
		}
	}

	private void initializeProjectData() {
		final DataFactory dataFactory = new DataFactory();
		for (int i = 0; i < PROJECT_COUNT; i++) {
			final Project project = new Project(UUID.randomUUID(), dataFactory.getBusinessName(),
					dataFactory.getRandomText(50), dataFactory.getRandomText(250), getRandomProjectState(),
					getRandomUser(), LocalDateTime.now(), LocalDateTime.now(), getRandomLocation(),
					getRandomInvestmentGoal(), getRandomSlots(), getRandomSupporters());
			projectRepo.create(project);
		}
	}

	private void initializeUserData() {
		final DataFactory dataFactory = new DataFactory();
		for (int i = 0; i < USER_COUNT; i++) {
			final List<Skill> skills = getRandomSkills();
			final User user = new User(UUID.randomUUID(), dataFactory.getFirstName(), dataFactory.getLastName(),
					getRandomUserType(), skills, dataFactory.getRandomText(250));
			userRepo.createUser(user);
		}
	}

	private List<Supporter> getRandomSupporters() {
		// TODO SupporterRepo

		return Lists.newArrayList();
	}

	private List<Slot> getRandomSlots() {
		// TODO SlotRepo

		return Lists.newArrayList();
	}

	private double getRandomInvestmentGoal() {
		// TODO Sinnvolle goals?

		return Math.random();
	}

	private Location getRandomLocation() {
		// TODO Random

		return new Location(0.0, 0.0);
	}

	private User getRandomUser() {
		final Random random = new Random();
		final Integer randomOffset = random.nextInt(USER_COUNT);
		return userRepo.getUsers(randomOffset.longValue(), USER_COUNT.longValue()).get(0);
	}

	private ProjectState getRandomProjectState() {
		final Random random = new Random();
		return ProjectState.values()[random.nextInt(ProjectState.values().length)];
	}

	private UserType getRandomUserType() {
		final Random random = new Random();
		return UserType.values()[random.nextInt(UserType.values().length)];
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
}
