package de.funding.funding.core.repository;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import de.funding.funding.entity.Image;
import de.funding.funding.entity.User;

public interface UserRepository {

	void createUser(User user);

	User getUser(UUID uuid);

	List<User> getUsers(Long offset, Long limit);

	Image getUserAvatar(UUID projectUUid);
}
