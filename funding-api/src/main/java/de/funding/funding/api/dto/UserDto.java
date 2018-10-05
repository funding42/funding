package de.funding.funding.api.dto;

import de.funding.funding.entity.Skill;
import de.funding.funding.entity.User;

import java.net.URL;
import java.util.List;
import java.util.UUID;

public class UserDto {
  private UUID uuid;
  private String prename;
  private String surname;
  private User.Type type;
  private List<Skill> skills;
  private String description;
  private URL avatarUrl;

  public UserDto(final UUID uuid, final String prename, final String surname, final User.Type type, final List<Skill> skills, final String description, final URL avatarUrl) {
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

  public User.Type getType() {
    return type;
  }

  public void setType(final User.Type type) {
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

  public URL getAvatarUrl() {
    return avatarUrl;
  }

  public void setAvatarUrl(final URL avatarUrl) {
    this.avatarUrl = avatarUrl;
  }
}
