package de.funding.funding.api.controller;

import de.funding.funding.api.dto.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController("/api/v1/user")
public class UserController {

  @GetMapping("/{uuid}")
  public UserDto getUser(UUID uuid) {
    throw new UnsupportedOperationException();
  }
}
