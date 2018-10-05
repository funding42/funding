package de.funding.funding.repository;

import de.funding.funding.entity.PersistentVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaVoteRepository extends JpaRepository<PersistentVote, UUID> {

}