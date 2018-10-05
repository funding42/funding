package de.funding.funding.entity;

import java.util.UUID;

public class Slot {

	private UUID uuid;
	private Supporter supporter;
	private Skill skill;

	public Slot() {
	}

	public Slot(final UUID uuid, final Supporter supporter, final Skill skill) {
		this.uuid = uuid;
		this.supporter = supporter;
		this.skill = skill;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(final UUID uuid) {
		this.uuid = uuid;
	}

	public Supporter getSupporter() {
		return supporter;
	}

	public void setSupporter(final Supporter supporter) {
		this.supporter = supporter;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(final Skill skill) {
		this.skill = skill;
	}
}
