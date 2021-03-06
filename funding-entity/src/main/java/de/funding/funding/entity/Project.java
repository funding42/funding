package de.funding.funding.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Project {
	private UUID uuid;
	private String title;
	private String teaser;
	private String description;
	private ProjectState state;
	private User creator;
	private LocalDateTime createdAt;
	private LocalDateTime lastModified;
	private Location location;
	private Double investmentGoal;
	private List<Slot> slots;
	private List<Supporter> supporters;

	public Project() {
	}

	public Project(final UUID uuid, final String title, final String teaser, final String description, final ProjectState state, final User creator, final LocalDateTime createdAt, final LocalDateTime lastModified, final Location location, final Double investmentGoal, final List<Slot> slots, final List<Supporter> supporters) {
		this.uuid = uuid;
		this.title = title;
		this.teaser = teaser;
		this.description = description;
		this.state = state;
		this.creator = creator;
		this.createdAt = createdAt;
		this.lastModified = lastModified;
		this.location = location;
		this.investmentGoal = investmentGoal;
		this.slots = slots;
		this.supporters = supporters;
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

	public ProjectState getState() {
		return state;
	}

	public void setState(final ProjectState state) {
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

	public Location getLocation() {
		return location;
	}

	public void setLocation(final Location location) {
		this.location = location;
	}

	public Double getInvestmentGoal() {
		return investmentGoal;
	}

	public void setInvestmentGoal(final Double investmentGoal) {
		this.investmentGoal = investmentGoal;
	}

	public List<Slot> getSlots() {
		return slots;
	}

	public void setSlots(final List<Slot> slots) {
		this.slots = slots;
	}

	public List<Supporter> getSupporters() {
		return supporters;
	}

	public void setSupporters(final List<Supporter> supporters) {
		this.supporters = supporters;
	}
}
