package de.funding.funding.api.controller.user;

import de.funding.funding.api.dto.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/user")
public class UserController {

  @GetMapping("/{uuid}")
  public UserDto getUser(@PathVariable("uuid") final UUID uuid) {
    throw new UnsupportedOperationException();
  }
}