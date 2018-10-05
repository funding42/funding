package de.funding.funding.api.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class ProjectListDto {
	public enum State {New, Accepted, Validated}

	private UUID uuid;
	private String title;
	private String teaser;
	private State state;
	private UserOverviewDto creator;
	private LocalDateTime createdAt;
	private LocalDateTime lastModified;
	private long numSupporters;
	private double score;
	private double votes;
	private double percentUpvotes;
	private String teaserPictureUrl;

	public ProjectListDto() {
	}

	public ProjectListDto(final UUID uuid, final String title, final String teaser, final State state,
			final UserOverviewDto creator, final LocalDateTime createdAt, final LocalDateTime lastModified,
			final long numSupporters, final double score, final double votes, final double percentUpvotes,
			final String teaserPictureUrl) {
		super();
		this.uuid = uuid;
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
		this.teaserPictureUrl = teaserPictureUrl;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(final UUID uuid) {
		this.uuid = uuid;
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

	public State getState() {
		return state;
	}

	public void setState(final State state) {
		this.state = state;
	}

	public UserOverviewDto getCreator() {
		return creator;
	}

	public void setCreator(final UserOverviewDto creator) {
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

	public String getTeaserPictureUrl() {
		return teaserPictureUrl;
	}

	public void setTeaserPictureUrl(final String teaserPictureUrl) {
		this.teaserPictureUrl = teaserPictureUrl;
	}

}
