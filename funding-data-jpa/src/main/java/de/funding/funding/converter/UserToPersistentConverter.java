package de.funding.funding.converter;

import de.funding.funding.entity.PersistentUser;
import de.funding.funding.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToPersistentConverter implements Converter<User, PersistentUser> {

  @Override
  public PersistentUser convert(final User source) {
    return null;
  }
}
