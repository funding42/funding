package de.funding.funding.api.converter;

import de.funding.funding.api.dto.UserDto;
import de.funding.funding.entity.UserType;
import org.springframework.core.convert.converter.Converter;

public class UserTypeToTypeDtoConverter implements Converter<UserType, UserDto.Type> {
  @Override
  public UserDto.Type convert(final UserType userType) {
    if(userType == null) return null;
    switch (userType) {
      case Citizen: return UserDto.Type.Citizen;
      case Commercial: return UserDto.Type.Commercial;
      case PublicInstitution: return UserDto.Type.PublicInstitution;
      default: throw new IllegalArgumentException("Unknown Type: " + userType);
    }
  }
}
