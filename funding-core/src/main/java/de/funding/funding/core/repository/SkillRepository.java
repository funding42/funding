package de.funding.funding.core.repository;

import de.funding.funding.entity.Skill;

import java.util.List;

public interface SkillRepository {

  List<Skill> findAll();

  void add(Skill skill);
}
