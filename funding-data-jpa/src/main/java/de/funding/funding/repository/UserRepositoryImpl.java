package de.funding.funding.repository;

import de.funding.funding.converter.PersistentUserToUserConverter;
import de.funding.funding.converter.UserToPersistentConverter;
import de.funding.funding.core.repository.UserRepository;
import de.funding.funding.entity.Image;
import de.funding.funding.entity.PersistentProject;
import de.funding.funding.entity.PersistentUser;
import de.funding.funding.entity.User;
import de.funding.funding.util.OffsetBasedPageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    delegator.save(persistentUser);
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
  public Image getUserAvatar(final UUID userUuid) {
    final PersistentUser persistentUser = delegator.findById(userUuid).orElse(null);
    if (persistentUser != null && persistentUser.getAvatar() != null && persistentUser.getAvatarMimetype() != null) {
      return new UserAvatar(persistentUser);
    } else {
      return null;
    }
  }

  private static class UserAvatar implements Image {

    private final PersistentUser persistentUser;

    public UserAvatar(final PersistentUser persistentUser) {
      this.persistentUser = persistentUser;
    }

    @Override
    public InputStream getStream() throws IOException {
      try {
        return persistentUser.getAvatar() != null? persistentUser.getAvatar().getBinaryStream():null;
      } catch (SQLException e) {
        throw new IOException(e);
      }
    }

    @Override
    public String getMimeType() {
      return persistentUser.getAvatarMimetype();
    }
  }



}
