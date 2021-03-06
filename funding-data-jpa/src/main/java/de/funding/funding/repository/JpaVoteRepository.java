package de.funding.funding.repository;

import de.funding.funding.entity.PersistentProject;
import de.funding.funding.entity.PersistentVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Repository
public interface JpaVoteRepository extends JpaRepository<PersistentVote, UUID> {

  Set<PersistentVote> findAllByProject(PersistentProject project);

  Set<PersistentVote> findAllByProjectAndCreatedAtGreaterThan(PersistentProject project, LocalDateTime since);

  Set<PersistentVote> findByCreatedAtGreaterThan(LocalDateTime since);
}
