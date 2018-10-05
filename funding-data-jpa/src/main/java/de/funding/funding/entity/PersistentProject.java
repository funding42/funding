package de.funding.funding.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "FUN_Project")
public class PersistentProject extends AbstractPersistentEntity {

  private String title;

  private String teaser;

  private String description;

  private ProjectState state;

  private PersistentUser creator;

  @Column(name = "title", nullable = false)
  public String getTitle() {
    return title;
  }

  public void setTitle(final String title) {
    this.title = title;
  }

  @Column(name = "teaser", nullable = false)
  public String getTeaser() {
    return teaser;
  }

  public void setTeaser(final String teaser) {
    this.teaser = teaser;
  }

  @Column(name = "description", nullable = false)
  public String getDescription() {
    return description;
  }

  public void setDescription(final String description) {
    this.description = description;
  }

  @Column(name = "project_state", nullable = false)
  public ProjectState getState() {
    return state;
  }

  public void setState(final ProjectState state) {
    this.state = state;
  }

  @ManyToOne(targetEntity = PersistentUser.class, optional = false)
  @JoinColumn(name = "creator_id", nullable = false,
      foreignKey = @ForeignKey(name = "FK_FUN_Project_creator"))
  public PersistentUser getCreator() {
    return creator;
  }

  public void setCreator(final PersistentUser creator) {
    this.creator = creator;
  }
}
