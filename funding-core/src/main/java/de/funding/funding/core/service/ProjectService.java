package de.funding.funding.core.service;

import de.funding.funding.core.repository.ProjectRepository;
import de.funding.funding.core.repository.VoteRepository;
import de.funding.funding.entity.Project;

import java.time.LocalDateTime;
import java.util.List;

public class ProjectService {

  private static final long PAGE_SIZE = 50;

  private final ProjectRepository projectRepository;
  private final VoteRepository voteRepository;

  public ProjectService(final ProjectRepository projectRepository, final VoteRepository voteRepository) {
    this.projectRepository = projectRepository;
    this.voteRepository = voteRepository;
  }

  public void calculateScores() {

    long offset = 0;

    final LocalDateTime lastHour = LocalDateTime.now().minusHours(1);

    List<Project> projects;
    do {
      projects = projectRepository.getProjects(offset, offset + PAGE_SIZE, null, null);
      for(Project project: projects) {
        final VoteRepository.SumProjection sumProjection = voteRepository.countVotes(project);
        final long popularity = sumProjection.getNumUpvotes() - sumProjection.getNumDownvotes();
        projectRepository.cachePopularity(project, popularity);

        final VoteRepository.SumProjection trendingSum = voteRepository.countVotesSince(project, lastHour);
        final long trendingScore = trendingSum.getNumUpvotes() - trendingSum.getNumDownvotes();
        projectRepository.cacheTrendingScore(project, trendingScore);
      }
    } while (!projects.isEmpty());
  }
}
