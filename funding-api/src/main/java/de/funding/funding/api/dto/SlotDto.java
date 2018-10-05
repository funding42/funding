package de.funding.funding.api.dto;

import java.util.UUID;

public class SlotDto {
  private UUID uuid;
  private SupporterDto supporter;
  private String skill;

  public SlotDto(final UUID uuid, final SupporterDto supporter, final String skill) {
    this.uuid = uuid;
    this.supporter = supporter;
    this.skill = skill;
  }

  public SlotDto() {
  }

  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(final UUID uuid) {
    this.uuid = uuid;
  }

  public SupporterDto getSupporter() {
    return supporter;
  }

  public void setSupporter(final SupporterDto supporter) {
    this.supporter = supporter;
  }

  public String getSkill() {
    return skill;
  }

  public void setSkill(final String skill) {
    this.skill = skill;
  }
}
