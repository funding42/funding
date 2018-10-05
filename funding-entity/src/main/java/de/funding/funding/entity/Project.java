package de.funding.funding.entity;

import java.time.LocalDateTime;

public class Project {
  public enum State {New, Accepted, Validated}
  private String title;
  private String teaser;
  private String description;
  private State state;
  private User creator;
  private LocalDateTime createdAt;
  private LocalDateTime lastModified;

  public Project(final String title, final String teaser, final String description, final State state, final User creator, final LocalDateTime createdAt, final LocalDateTime lastModified) {
    this.title = title;
    this.teaser = teaser;
    this.description = description;
    this.state = state;
    this.creator = creator;
    this.createdAt = createdAt;
    this.lastModified = lastModified;
  }

  public Project() {
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(final String title) {
    this.title = title;
  }

  public String getTeaser() {
    return teaser;
  }

  public void setTeaser(final String teaser) {
    this.teaser = teaser;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(final String description) {
    this.description = description;
  }

  public State getState() {
    return state;
  }

  public void setState(final State state) {
    this.state = state;
  }

  public User getCreator() {
    return creator;
  }

  public void setCreator(final User creator) {
    this.creator = creator;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(final LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getLastModified() {
    return lastModified;
  }

  public void setLastModified(final LocalDateTime lastModified) {
    this.lastModified = lastModified;
  }
}
