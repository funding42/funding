package de.funding.funding.entity;

public class Skill {
  private String name;
  private String description;

  public Skill(final String name, final String description) {
    this.name = name;
    this.description = description;
  }

  public Skill() {
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(final String description) {
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }
}
