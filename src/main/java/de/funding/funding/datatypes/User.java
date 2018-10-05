package de.funding.funding.datatypes;

import java.util.List;

public class User {
  public enum Type {Citizen, Commercial, PublicInstitution}
  private String prename;
  private String surname;
  private Type type;
  private List<Skill> skills;
  private String description;

  public User(final String prename, final String surname, final Type type, final List<Skill> skills, final String description) {
    this.prename = prename;
    this.surname = surname;
    this.type = type;
    this.skills = skills;
    this.description = description;
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
