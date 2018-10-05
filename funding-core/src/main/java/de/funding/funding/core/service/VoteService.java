package de.funding.funding.core.service;

import de.funding.funding.core.repository.VoteRepository;
import de.funding.funding.entity.Project;

public class VoteService {

  private final VoteRepository voteRepository;

  public VoteService(final VoteRepository voteRepository) {
    this.voteRepository = voteRepository;
  }

  public VoteResult calculateVoteResult(Project project) {
    final VoteRepository.SumProjection sumProjection = voteRepository.countVotes(project);

    long sum = sumProjection.getNumDownvotes() + sumProjection.getNumUpvotes();

    return new VoteResult(sumProjection.getNumUpvotes() - sumProjection.getNumDownvotes(), (double) sumProjection.getNumUpvotes() / (double) sum);
  }

  public static class VoteResult {
    private final long votes;
    private final double percentUpvotes;

    public VoteResult(final long upvotes, final double percentUpvotes) {
      this.votes = upvotes;
      this.percentUpvotes = percentUpvotes;
    }

    public long getVotes() {
      return votes;
    }

    public double getPercentUpvotes() {
      return percentUpvotes;
    }
  }

}
