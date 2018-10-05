package de.funding.funding.repository;

import de.funding.funding.entity.PersistentSupporter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaSupporterRepository extends JpaRepository<PersistentSupporter, UUID> {

}
