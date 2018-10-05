package de.funding.funding.api.dto;

import de.funding.funding.entity.Skill;
import de.funding.funding.entity.User;

import java.util.List;
import java.util.UUID;

public class UserOverviewDto {
  private UUID uuid;
  private String prename;
  private String surname;
  private UserDto.Type type;
  private String avatarUrl;


  public UserOverviewDto() {
  }

  public UserOverviewDto(final UUID uuid, final String prename, final String surname, final UserDto.Type type, final String avatarUrl) {
    this.uuid = uuid;
    this.prename = prename;
    this.surname = surname;
    this.type = type;
    this.avatarUrl = avatarUrl;
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

  public UserDto.Type getType() {
    return type;
  }

  public void setType(final UserDto.Type type) {
    this.type = type;
  }

  public String getAvatarUrl() {
    return avatarUrl;
  }

  public void setAvatarUrl(final String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }
}