package de.funding.funding.api.dto;

import de.funding.funding.datatypes.Project;
import de.funding.funding.datatypes.Supporter;
import de.funding.funding.datatypes.User;

import java.time.LocalDateTime;
import java.util.List;

public class ProjectDto {
  public enum State {New, Accepted, Validated}
  private String title;
  private String teaser;
  private String description;
  private Project.State state;
  private User creator;
  private LocalDateTime createdAt;
  private LocalDateTime lastModified;
  private List<Supporter> supporters;
  private double votes;
  private double percentUpvotes;

  public ProjectDto(final String title, final String teaser, final String description, final Project.State state, final User creator, final LocalDateTime createdAt, final LocalDateTime lastModified, final List<Supporter> supporters, final double votes, final double percentUpvotes) {
    this.title = title;
    this.teaser = teaser;
    this.description = description;
    this.state = state;
    this.creator = creator;
    this.createdAt = createdAt;
    this.lastModified = lastModified;
    this.supporters = supporters;
    this.votes = votes;
    this.percentUpvotes = percentUpvotes;
  }

  public ProjectDto() {
  }

  public double getPercentUpvotes() {
    return percentUpvotes;
  }

  public void setPercentUpvotes(final double percentUpvotes) {
    this.percentUpvotes = percentUpvotes;
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

  public Project.State getState() {
    return state;
  }

  public void setState(final Project.State state) {
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

  public List<Supporter> getSupporters() {
    return supporters;
  }

  public void setSupporters(final List<Supporter> supporters) {
    this.supporters = supporters;
  }

  public double getVotes() {
    return votes;
  }

  public void setVotes(final double votes) {
    this.votes = votes;
  }
}
