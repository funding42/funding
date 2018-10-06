package de.funding.funding.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.funding.funding.entity.PersistentSlot;

@Repository
public interface JpaSlotRepository extends JpaRepository<PersistentSlot, UUID> {

}
