package de.funding.funding.core.repository;

import de.funding.funding.entity.Project;
import de.funding.funding.entity.Vote;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public interface VoteRepository {

  void add(Vote vote);

  SumProjection countVotes(Project project);

  SumProjection countVotesSince(Project project, LocalDateTime since);

  Set<UUID> getProjectsVotedSince(LocalDateTime since);

  interface SumProjection {

    long getNumUpvotes();

    long getNumDownvotes();
  }
}
