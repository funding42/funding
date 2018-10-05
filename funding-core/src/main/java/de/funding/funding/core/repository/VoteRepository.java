package de.funding.funding.core.repository;

import de.funding.funding.entity.Project;
import de.funding.funding.entity.Vote;

public interface VoteRepository {

  void add(Vote vote);

  SumProjection countVotes(Project project);

  interface SumProjection {

    long getNumUpvotes();

    long getNumDownvotes();
  }
}
