package de.funding.funding.datatypes;

import java.util.List;

public class Supporter {
  private User user;
  private List<Skill> skills;
  private Double amountInvested;

  public Supporter(final User user, final List<Skill> skills, final Double amountInvested) {
    this.user = user;
    this.skills = skills;
    this.amountInvested = amountInvested;
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
