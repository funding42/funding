package de.funding.funding.api.controller.user;

import de.funding.funding.api.dto.UserDto;
import de.funding.funding.core.repository.UserRepository;
import de.funding.funding.entity.Skill;
import de.funding.funding.entity.User;
import de.funding.funding.entity.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/user")
public class UserController {

  private final UserRepository userRepository;

  private final DefaultConversionService defaultConversionService;

  @Autowired
  public UserController(final UserRepository userRepository, final DefaultConversionService defaultConversionService) {
    this.userRepository = userRepository;
    this.defaultConversionService = defaultConversionService;
  }

  @GetMapping("/{uuid}")
  public UserDto getUser(@PathVariable("uuid") final UUID uuid) {
    User user = userRepository.getUser(uuid);

    if(user == null) {
      throw new UserNotFoundException(uuid);
    }

    final UserDto userDto = defaultConversionService.convert(user, UserDto.class);
    userDto.setAvatarUrl(userRepository.getAvatarUri(user.getUuid()).toString());
    return userDto;
  }



  public static class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(UUID uuid) {
      super("User not found: " + uuid);
    }
  }
}
