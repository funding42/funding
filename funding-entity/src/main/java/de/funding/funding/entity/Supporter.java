package de.funding.funding.entity;

import java.util.List;
import java.util.UUID;

public class Supporter {
	private UUID uuid;
	private User user;
	private List<Skill> skills;
	private Double amountInvested;
	private Boolean fillsSlot;

	public Supporter() {
	}

	public Supporter(final UUID uuid, final User user, final List<Skill> skills, final Double amountInvested,
			final Boolean fillsSlot) {
		super();
		this.uuid = uuid;
		this.user = user;
		this.skills = skills;
		this.amountInvested = amountInvested;
		this.fillsSlot = fillsSlot;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(final UUID uuid) {
		this.uuid = uuid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(final List<Skill> skills) {
		this.skills = skills;
	}

	public Double getAmountInvested() {
		return amountInvested;
	}

	public void setAmountInvested(final Double amountInvested) {
		this.amountInvested = amountInvested;
	}

	public Boolean getFillsSlot() {
		return fillsSlot;
	}

	public void setFillsSlot(final Boolean fillsSlot) {
		this.fillsSlot = fillsSlot;
	}


}
