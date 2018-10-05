package de.funding.funding.entity;

import java.util.List;
import java.util.UUID;

public class User {
  public enum Type {Citizen, Commercial, PublicInstitution}
  private UUID uuid;
  private String prename;
  private String surname;
  private Type type;
  private List<Skill> skills;
  private String description;

  public User(final UUID uuid, final String prename, final String surname, final Type type, final List<Skill> skills, final String description) {
    this.uuid = uuid;
    this.prename = prename;
    this.surname = surname;
    this.type = type;
    this.skills = skills;
    this.description = description;
  }

  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(final UUID uuid) {
    this.uuid = uuid;
  }

  public void setPrename(final String prename) {
    this.prename = prename;
  }

  public void setSurname(final String surname) {
    this.surname = surname;
  }

  public void setType(final Type type) {
    this.type = type;
  }

  public void setSkills(final List<Skill> skills) {
    this.skills = skills;
  }

  public void setDescription(final String description) {
    this.description = description;
  }

  public String getPrename() {
    return prename;
  }

  public String getSurname() {
    return surname;
  }

  public Type getType() {
    return type;
  }

  public List<Skill> getSkills() {
    return skills;
  }

  public String getDescription() {
    return description;
  }
}
