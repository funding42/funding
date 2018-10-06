package de.funding.funding.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Blob;

@Entity
@Table(name = "FUN_Project")
public class PersistentProject extends AbstractPersistentEntity {

  private String title;

  private String teaser;

  private String description;

  private ProjectState state;

  private PersistentUser creator;

  private EmbeddableLocation location;

  private Double fundingGoal;

  private Blob image;

  private String imageMimetype;

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

  @Embedded
  @AttributeOverrides({ //
      @AttributeOverride(name = "longitude", column = @Column(name = "project_lon")), //
      @AttributeOverride(name = "latitude", column = @Column(name = "project_lan")) //
  })
  public EmbeddableLocation getLocation() {
    return location;
  }

  public void setLocation(final EmbeddableLocation location) {
    this.location = location;
  }

  @Column(name = "funding_goal")
  public Double getFundingGoal() {
    return fundingGoal;
  }

  public void setFundingGoal(final Double fundingGoal) {
    this.fundingGoal = fundingGoal;
  }

  @Column(name = "image")
  public Blob getImage() {
    return image;
  }

  public void setImage(final Blob image) {
    this.image = image;
  }

  @Column(name = "image_mimetype")
  public String getImageMimetype() {
    return imageMimetype;
  }

  public void setImageMimetype(final String imageMimetype) {
    this.imageMimetype = imageMimetype;
  }
}
