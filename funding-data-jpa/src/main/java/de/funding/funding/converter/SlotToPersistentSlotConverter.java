package de.funding.funding.converter;

import de.funding.funding.entity.PersistentSlot;
import de.funding.funding.entity.Slot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SlotToPersistentSlotConverter implements Converter<Slot, PersistentSlot> {

  @Autowired
  private SkillToPersistentSkillConverter skillConverter;

  @Autowired
  private SupporterToPersistentSupporterConverter supporterConverter;

  @Override
  public PersistentSlot convert(final Slot source) {
    final PersistentSlot result = new PersistentSlot();
    result.setId(source.getUuid());
    result.setSkill(skillConverter.convert(source.getSkill()));
    result.setSupporter(supporterConverter.convert(source.getSupporter()));
    return result;
  }
}
