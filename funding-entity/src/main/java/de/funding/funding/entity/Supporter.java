package de.funding.funding.entity;

import java.util.List;
import java.util.UUID;

public class Supporter {
  private UUID uuid;
  private User user;
  private List<Skill> skills;
  private Double amountInvested;

  public Supporter(final UUID uuid, final User user, final List<Skill> skills, final Double amountInvested) {
    this.uuid = uuid;
    this.user = user;
    this.skills = skills;
    this.amountInvested = amountInvested;
  }

  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(final UUID uuid) {
    this.uuid = uuid;
  }

  public List<Skill> getSkills() {
    return skills;
  }

  public void setSkills(final List<Skill> skills) {
    this.skills = skills;
  }

  public Double getAmountInvested() {
    return amountInvested;
  }

  public void setAmountInvested(final Double amountInvested) {
    this.amountInvested = amountInvested;
  }

  public Supporter() {
  }

  public User getUser() {
    return user;
  }

  public void setUser(final User user) {
    this.user = user;
  }
}
