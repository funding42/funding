package de.funding.funding.repository;

import de.funding.funding.entity.PersistentSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaSkillRepository extends JpaRepository<PersistentSkill, UUID> {

}
