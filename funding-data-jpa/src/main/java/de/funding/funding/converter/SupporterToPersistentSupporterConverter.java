package de.funding.funding.converter;

import de.funding.funding.entity.PersistentSupporter;
import de.funding.funding.entity.Supporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class SupporterToPersistentSupporterConverter
    implements Converter<Supporter, PersistentSupporter> {

  @Autowired
  private UserToPersistentConverter userConverter;

  @Autowired
  private SkillToPersistentSkillConverter skillConverter;

  @Override
  public PersistentSupporter convert(final Supporter source) {
    final PersistentSupporter result = new PersistentSupporter();
    result.setId(source.getUuid());
    result.setAmountInvested(source.getAmountInvested());
    result.setUser(userConverter.convert(source.getUser()));
    result.setSkills(
        source.getSkills().stream().map(skillConverter::convert).collect(Collectors.toSet()));
    return null;
  }
}
