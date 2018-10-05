package de.funding.funding.api;

import de.funding.funding.api.dto.UserDto;
import de.funding.funding.datatypes.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController("/api/v1/user")
public class UserController {

  @GetMapping("/{uuid}")
  public UserDto getUser(UUID uuid) {
    throw new UnsupportedOperationException();
  }
}
