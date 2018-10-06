package de.funding.funding.repository;

import de.funding.funding.converter.ProjectToPersistentProjectConverter;
import de.funding.funding.converter.UserToPersistentConverter;
import de.funding.funding.converter.VoteToPersistentVoteConverter;
import de.funding.funding.core.repository.VoteRepository;
import de.funding.funding.entity.PersistentProject;
import de.funding.funding.entity.PersistentVote;
import de.funding.funding.entity.Project;
import de.funding.funding.entity.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class VoteRepositoryImpl implements VoteRepository {

  @Autowired
  private JpaVoteRepository delegate;

  @Autowired
  private JpaProjectRepository projectRepository;

  @Autowired
  private ProjectToPersistentProjectConverter projectToPersistentProjectConverter;

  @Autowired
  private UserToPersistentConverter userToPersistentConverter;

  @Autowired
  private VoteToPersistentVoteConverter voteToPersistentVoteConverter;

  @Override
  public void add(final Vote vote) {
    PersistentVote entity = voteToPersistentVoteConverter.convert(vote);
    delegate.save(entity);
  }

  @Override
  public SumProjection countVotes(final Project project) {
    final PersistentProject persistentProject =
        projectRepository.findById(project.getUuid()).orElse(null);
    final Set<PersistentVote> votes = delegate.findAllByProject(persistentProject);
    final long upvotes = votes.stream().filter(PersistentVote::isUpvote).count();
    final long downvotes = votes.stream().filter(PersistentVote::isDownvote).count();
    return new DefaultSumProjection(upvotes, downvotes);
  }

  @Override
  public SumProjection countVotesSince(final Project project, final LocalDateTime since) {
    final PersistentProject persistentProject =
            projectRepository.findById(project.getUuid()).orElse(null);
    final Set<PersistentVote> votes = delegate.findAllByProjectAndCreatedAtGreaterThan(persistentProject, since);
    final long upvotes = votes.stream().filter(PersistentVote::isUpvote).count();
    final long downvotes = votes.stream().filter(PersistentVote::isDownvote).count();
    return new DefaultSumProjection(upvotes, downvotes);
  }

  @Override
  public Set<UUID> getProjectsVotedSince(final LocalDateTime since) {
    final Set<PersistentVote> byCreatedAtGreaterThan = delegate.findByCreatedAtGreaterThan(since);
    return byCreatedAtGreaterThan.stream().map(v -> v.getProject().getId()).collect(Collectors.toSet());
  }

  public class DefaultSumProjection implements SumProjection {

    private long numUpvotes;

    private long numDownvotes;

    private DefaultSumProjection(final long numUpvotes, final long numDownvotes) {
      this.numUpvotes = numUpvotes;
      this.numDownvotes = numDownvotes;
    }

    @Override
    public long getNumUpvotes() {
      return numUpvotes;
    }

    @Override
    public long getNumDownvotes() {
      return numDownvotes;
    }
  }
}
