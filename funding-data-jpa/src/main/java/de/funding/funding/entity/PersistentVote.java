package de.funding.funding.entity;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "FUN_Vote")
public class PersistentVote extends AbstractPersistentEntity {

  private PersistentProject project;

  private PersistentUser user;

  @ManyToOne(targetEntity = PersistentProject.class, optional = false)
  @JoinColumn(name = "project_id", foreignKey = @ForeignKey(name = "FK_FUN_Vote_project"))
  public PersistentProject getProject() {
    return project;
  }

  public void setProject(final PersistentProject project) {
    this.project = project;
  }

  @ManyToOne(targetEntity = PersistentUser.class, optional = false)
  @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_FUN_Vote_user"))
  public PersistentUser getUser() {
    return user;
  }

  public void setUser(final PersistentUser user) {
    this.user = user;
  }
}
