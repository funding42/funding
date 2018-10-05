package de.funding.funding.entity;

import java.util.UUID;

public class Skill {
  private UUID uuid;
  private String name;
  private String description;

  public Skill(final UUID uuid, final String name, final String description) {
    this.uuid = uuid;
    this.name = name;
    this.description = description;
  }

  public Skill() {
  }

  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(final UUID uuid) {
    this.uuid = uuid;
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
