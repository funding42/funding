package de.funding.funding.api.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ProjectDto {
	public enum State {New, Accepted, Validated}
	private UUID uuid;
	private String title;
	private String teaser;
	private String description;
	private State state;
	private UserDto creator;
	private LocalDateTime createdAt;
	private LocalDateTime lastModified;
	private List<SupporterDto> supporters;
	private double votes;
	private double percentUpvotes;
	private double investmentGoal;
	private double investmentProgress;
	private String teaserPictureUrl;

	public ProjectDto() {
	}

	public ProjectDto(final UUID uuid, final String title, final String teaser, final String description,
			final State state, final UserDto creator, final LocalDateTime createdAt, final LocalDateTime lastModified,
			final List<SupporterDto> supporters, final double votes, final double percentUpvotes,
			final double investmentGoal, final double investmentProgress, final String teaserPictureUrl) {
		super();
		this.uuid = uuid;
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
		this.investmentGoal = investmentGoal;
		this.investmentProgress = investmentProgress;
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

	public UserDto getCreator() {
		return creator;
	}

	public void setCreator(final UserDto creator) {
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

	public List<SupporterDto> getSupporters() {
		return supporters;
	}

	public void setSupporters(final List<SupporterDto> supporters) {
		this.supporters = supporters;
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

	public double getInvestmentGoal() {
		return investmentGoal;
	}

	public void setInvestmentGoal(final double investmentGoal) {
		this.investmentGoal = investmentGoal;
	}

	public double getInvestmentProgress() {
		return investmentProgress;
	}

	public void setInvestmentProgress(final double investmentProgress) {
		this.investmentProgress = investmentProgress;
	}

	public String getTeaserPictureUrl() {
		return teaserPictureUrl;
	}

	public void setTeaserPictureUrl(final String teaserPictureUrl) {
		this.teaserPictureUrl = teaserPictureUrl;
	}

}
