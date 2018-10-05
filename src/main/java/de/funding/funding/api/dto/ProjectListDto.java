package de.funding.funding.api.dto;

import de.funding.funding.datatypes.Project;
import de.funding.funding.datatypes.Supporter;
import de.funding.funding.datatypes.User;

import java.time.LocalDateTime;
import java.util.List;

public class ProjectListDto {
  public enum State {New, Accepted, Validated}
  private String title;
  private String teaser;
  private Project.State state;
  private User creator;
  private LocalDateTime createdAt;
  private LocalDateTime lastModified;
  private long numSupporters;
  private double score;
  private double votes;
  private double percentUpvotes;

  public ProjectListDto(final String title, final String teaser, final Project.State state, final User creator, final LocalDateTime createdAt, final LocalDateTime lastModified, final long numSupporters, final double score, final double votes, final double percentUpvotes) {
    this.title = title;
    this.teaser = teaser;
    this.state = state;
    this.creator = creator;
    this.createdAt = createdAt;
    this.lastModified = lastModified;
    this.numSupporters = numSupporters;
    this.score = score;
    this.votes = votes;
    this.percentUpvotes = percentUpvotes;
  }

  public ProjectListDto() {
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

  public long getNumSupporters() {
    return numSupporters;
  }

  public void setNumSupporters(final long numSupporters) {
    this.numSupporters = numSupporters;
  }

  public double getScore() {
    return score;
  }

  public void setScore(final double score) {
    this.score = score;
  }

  public double getVotes() {
    return votes;
  }

  public void setVotes(final double votes) {
    this.votes = votes;
  }

  public double getPercentUpvotes() {
    return percentUpvotes;
  }

  public void setPercentUpvotes(final double percentUpvotes) {
    this.percentUpvotes = percentUpvotes;
  }
}
