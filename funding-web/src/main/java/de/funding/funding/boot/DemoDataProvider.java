package de.funding.funding.boot;

import com.github.javafaker.Faker;
import com.google.common.collect.Lists;
import de.funding.funding.core.boot.DataProvider;
import de.funding.funding.core.repository.ProjectRepository;
import de.funding.funding.core.repository.SkillRepository;
import de.funding.funding.core.repository.SlotRepository;
import de.funding.funding.core.repository.SupporterRepository;
import de.funding.funding.core.repository.UserRepository;
import de.funding.funding.entity.Location;
import de.funding.funding.entity.Project;
import de.funding.funding.entity.ProjectState;
import de.funding.funding.entity.Skill;
import de.funding.funding.entity.Slot;
import de.funding.funding.entity.Supporter;
import de.funding.funding.entity.User;
import de.funding.funding.entity.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

@Service
public class DemoDataProvider implements DataProvider {

  private static final Integer SKILL_COUNT = 10;
  private static final Integer USER_COUNT = 50;
  public static final Integer PROJECT_COUNT = 50;

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

  private LocalDateTime getRandomTime() {
    final Faker faker = new Faker(Locale.GERMAN);
    return Instant.ofEpochMilli(faker.date().between(new Date(2017 - 1900, 5, 1), new Date()).getTime())
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime();
  }

  private Location getRandomLocation() {
    final Random random = new Random();
    final double baseBreitengrad = 52.01069;
    final double baseLaengengrad = 7.31129;

    return new Location(
            baseBreitengrad - 0.5 * random.nextDouble(),
            baseLaengengrad + 0.4 * random.nextDouble()
    );
  }

  private void initializeProjectData() {
    final Faker faker = new Faker(Locale.GERMAN);
    Random random = new Random();
    for (int i = 0; i < PROJECT_COUNT; i++) {

      final double investmentGoal = random.nextDouble() * random.nextInt(50000);

      List<Slot> slots = new ArrayList<>();
      for (Skill skill : getRandomSkills()) {
        Slot slot = new Slot(
                UUID.randomUUID(),
                null,
                skill
        );
        slots.add(slot);
      }

      double spentInvestment = 0;
      final int numSupporter = random.nextInt(20);
      List<Supporter> supporters = new ArrayList<>();
      for (int j = 0; j < numSupporter; ++j) {
        final User randomUser = getRandomUser();
        final Skill skill = randomUser.getSkills() != null && !randomUser.getSkills().isEmpty() ? randomUser.getSkills().get(random.nextInt(randomUser.getSkills().size())) : null;
        double investment = Math.max(random.nextDouble() * (investmentGoal - spentInvestment), 0);
        spentInvestment = spentInvestment + investment;
        Supporter supporter = new Supporter(
                UUID.randomUUID(),
                getRandomUser(),
                Arrays.asList(skill),
                investment
        );
        slots.stream().filter(slot -> slot.getSupporter() == null && slot.getSkill().equals(skill)).findAny().ifPresent(slot -> slot.setSupporter(supporter));
        supporters.add(supporter);
      }

      Project project = new Project(
              UUID.randomUUID(),
              faker.chuckNorris().fact(),
              faker.gameOfThrones().quote(),
              faker.lorem().paragraph(5),
              getRandomProjectState(),
              getRandomUser(),
              getRandomTime(),
              getRandomTime(),
              getRandomLocation(),
              investmentGoal,
              slots,
              supporters
      );
      projectRepo.create(project);
    }
  }

  @Override
  public void load() {
    initializeSkillsData();
    initializeUserData();
    initializeProjectData();
  }
}
