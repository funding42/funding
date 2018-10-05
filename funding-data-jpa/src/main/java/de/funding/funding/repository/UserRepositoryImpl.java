package de.funding.funding.repository;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.funding.funding.converter.PersistentUserToUserConverter;
import de.funding.funding.converter.UserToPersistentConverter;
import de.funding.funding.core.repository.UserRepository;
import de.funding.funding.entity.PersistentUser;
import de.funding.funding.entity.User;
import de.funding.funding.util.OffsetBasedPageRequest;

@Component
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	private JpaUserRepository delegator;

	@Autowired
	private PersistentUserToUserConverter persistentUserToUserConverter;

	@Autowired
	private UserToPersistentConverter userToPersistentConverter;

	@Override
	public void createUser(final User user) {
		final PersistentUser persistentUser = userToPersistentConverter.convert(user);
		persistentUser.setCreatedAt(LocalDateTime.now());
		persistentUser.setUpdatedAt(LocalDateTime.now());
		delegator.saveAndFlush(persistentUser);
	}

	@Override
	public User getUser(final UUID uuid) {
		return delegator.findById(uuid).map(persistentUserToUserConverter::convert).orElse(null);
	}

	@Override
	public List<User> getUsers(final Long offset, final Long limit) {
		final OffsetBasedPageRequest pageable = new OffsetBasedPageRequest(offset, limit);
		return delegator.findAll(pageable).stream().map(persistentUserToUserConverter::convert)
				.collect(Collectors.toList());
	}

	@Override
	public URI getAvatarUri(final UUID userUuid) {
		throw new UnsupportedOperationException();
	}

}
