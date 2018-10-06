package de.funding.funding.api.controller.user;

import de.funding.funding.api.dto.UserDto;
import de.funding.funding.core.repository.UserRepository;
import de.funding.funding.entity.Image;
import de.funding.funding.entity.Skill;
import de.funding.funding.entity.User;
import de.funding.funding.entity.UserType;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
  public UserDto getUser(HttpServletRequest request, @PathVariable("uuid") final UUID uuid) {
    User user = userRepository.getUser(uuid);

    if(user == null) {
      throw new UserNotFoundException(uuid);
    }

    final UserDto userDto = defaultConversionService.convert(user, UserDto.class);
    userDto.setAvatarUrl(getImageUri(uuid, request));
    return userDto;
  }

  @GetMapping("/avatar/{uuid}")
  public void getProjectImage(@PathVariable("uuid") final UUID uuid, HttpServletResponse response) throws IOException {

    final User user = userRepository.getUser(uuid);

    if (user == null) {
      throw new UserNotFoundException(uuid);
    }

    final Image projectImage = userRepository.getUserAvatar(uuid);

    if(projectImage == null) {
      throw new UserNotFoundException(uuid);
    }

    response.setContentType(projectImage.getMimeType());
    IOUtils.copy(projectImage.getStream(), response.getOutputStream());
  }

  private String getImageUri(UUID projectUuid, HttpServletRequest request) {
    final String path = request.getRequestURL().toString().replace(request.getRequestURI(), request.getContextPath());
    return path + "/api/user/avatar/" + projectUuid;
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  public static class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(UUID uuid) {
      super("User not found: " + uuid);
    }
  }
}
