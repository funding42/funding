package de.funding.funding.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "FUN_Supporter")
public class PersistentSupporter extends AbstractPersistentEntity {

  private PersistentUser user;

  private Set<PersistentSkill> skills;

  private Double amountInvested;

  @ManyToOne(targetEntity = PersistentUser.class, optional = false)
  @JoinColumn(name = "user_id", nullable = false,
      foreignKey = @ForeignKey(name = "FK_FUN_Supporter_user"))
  public PersistentUser getUser() {
    return user;
  }

  public void setUser(final PersistentUser user) {
    this.user = user;
  }

  @ManyToMany(targetEntity = PersistentSkill.class)
  @JoinTable(name = "FUN_Supporter_Skill", //
      joinColumns = @JoinColumn(name = "supporter_id"), //
      inverseJoinColumns = @JoinColumn(name = "skill_id"), //
      foreignKey = @ForeignKey(name = "FK_FUN_Supporter_supporter"), //
      inverseForeignKey = @ForeignKey(name = "FK_FUN_Supporter_skill"))
  public Set<PersistentSkill> getSkills() {
    return skills;
  }

  public void setSkills(final Set<PersistentSkill> skills) {
    this.skills = skills;
  }

  @Column(name = "amount_invested")
  public Double getAmountInvested() {
    return amountInvested;
  }

  public void setAmountInvested(final Double amountInvested) {
    this.amountInvested = amountInvested;
  }
}
