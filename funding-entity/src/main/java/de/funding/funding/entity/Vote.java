package de.funding.funding.entity;

import java.util.UUID;

public class Vote {

  private UUID uuid;

  private Project project;

  private User user;

  private boolean upvote;

  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(final UUID uuid) {
    this.uuid = uuid;
  }

  public Project getProject() {
    return project;
  }

  public void setProject(final Project project) {
    this.project = project;
  }

  public User getUser() {
    return user;
  }

  public void setUser(final User user) {
    this.user = user;
  }

  public boolean isUpvote() {
    return upvote;
  }

  public void setUpvote(final boolean upvote) {
    this.upvote = upvote;
  }
}
