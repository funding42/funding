package de.funding.funding.api.dto;

import java.util.List;

public class SupporterDto {
	private UserOverviewDto user;
	private List<String> skills;
	private Double amountInvested;
	private Boolean fillsSlot;

	public SupporterDto(final UserOverviewDto user, final List<String> skills, final Double amountInvested,
			final Boolean fillsSlot) {
		super();
		this.user = user;
		this.skills = skills;
		this.amountInvested = amountInvested;
		this.fillsSlot = fillsSlot;
	}

	public UserOverviewDto getUser() {
		return user;
	}

	public void setUser(final UserOverviewDto user) {
		this.user = user;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(final List<String> skills) {
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
