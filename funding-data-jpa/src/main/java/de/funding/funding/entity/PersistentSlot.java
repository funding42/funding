package de.funding.funding.entity;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "FUN_Slot")
public class PersistentSlot extends AbstractPersistentEntity {

  private PersistentSupporter supporter;

  private PersistentSkill skill;

  @ManyToOne(targetEntity = PersistentSupporter.class, optional = false)
  @JoinColumn(name = "supporter_id", nullable = false,
      foreignKey = @ForeignKey(name = "FK_FUN_Slot_supporter"))
  public PersistentSupporter getSupporter() {
    return supporter;
  }

  public void setSupporter(final PersistentSupporter supporter) {
    this.supporter = supporter;
  }

  @ManyToOne(targetEntity = PersistentSkill.class, optional = false)
  @JoinColumn(name = "skill_id", nullable = false,
      foreignKey = @ForeignKey(name = "FK_FUN_Slot_skill"))
  public PersistentSkill getSkill() {
    return skill;
  }

  public void setSkill(final PersistentSkill skill) {
    this.skill = skill;
  }
}
