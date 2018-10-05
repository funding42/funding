package de.funding.funding.api.dto;

import de.funding.funding.entity.Skill;
import de.funding.funding.entity.User;

import java.net.URL;
import java.util.List;

public class UserDto {
  private String prename;
  private String surname;
  private User.Type type;
  private List<Skill> skills;
  private String description;
  private URL avatarUrl;
}
