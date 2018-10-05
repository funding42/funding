package de.funding.funding.repository;

import de.funding.funding.entity.PersistentProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaProjectRepository extends JpaRepository<PersistentProject, UUID> {

}
