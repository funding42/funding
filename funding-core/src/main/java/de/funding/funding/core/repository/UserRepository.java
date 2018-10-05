package de.funding.funding.core.repository;

import de.funding.funding.entity.User;

import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.UUID;

public interface UserRepository {
  User getUser(UUID uuid);
  List<User> getUsers(Long offset, Long limit);

  URI getAvatarUri(UUID userUuid);
}
