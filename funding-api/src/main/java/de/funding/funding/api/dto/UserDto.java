package de.funding.funding.api.dto;

import java.util.List;
import java.util.UUID;

import de.funding.funding.entity.Skill;

public class UserDto {
	public enum Type {Citizen, Commercial, PublicInstitution}
	private UUID uuid;
	private String prename;
	private String surname;
	private Type type;
	private List<Skill> skills;
	private String description;
	private String avatarUrl;

	public UserDto(final UUID uuid, final String prename, final String surname, final Type type, final List<Skill> skills, final String description, final String avatarUrl) {
		this.uuid = uuid;
		this.prename = prename;
		this.surname = surname;
		this.type = type;
		this.skills = skills;
		this.description = description;
		this.avatarUrl = avatarUrl;
	}

	public UserDto() {
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(final UUID uuid) {
		this.uuid = uuid;
	}

	public String getPrename() {
		return prename;
	}

	public void setPrename(final String prename) {
		this.prename = prename;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
	}

	public Type getType() {
		return type;
	}

	public void setType(final Type type) {
		this.type = type;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(final List<Skill> skills) {
		this.skills = skills;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(final String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
}
