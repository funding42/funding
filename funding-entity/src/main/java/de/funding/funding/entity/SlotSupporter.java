package de.funding.funding.entity;

import java.util.UUID;

public class SlotSupporter {

	private UUID uuid;
	private User user;
	private Project project;
	private Skill skill;

	public SlotSupporter() {
	}

	public SlotSupporter(final UUID uuid, final User user, final Project project, final Skill skill) {
		super();
		this.uuid = uuid;
		this.user = user;
		this.project = project;
		this.skill = skill;
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

	public Project getProject() {
		return project;
	}

	public void setProject(final Project project) {
		this.project = project;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(final Skill skill) {
		this.skill = skill;
	}

}
