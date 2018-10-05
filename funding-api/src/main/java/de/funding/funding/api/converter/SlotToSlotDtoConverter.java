package de.funding.funding.api.converter;

import de.funding.funding.api.dto.SlotDto;
import de.funding.funding.api.dto.SupporterDto;
import de.funding.funding.entity.Skill;
import de.funding.funding.entity.Slot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;

public class SlotToSlotDtoConverter implements Converter<Slot, SlotDto>{

  private final DefaultConversionService defaultConversionService;

  @Autowired
  public SlotToSlotDtoConverter(final DefaultConversionService defaultConversionService) {
    this.defaultConversionService = defaultConversionService;
  }

  protected String convert(Skill skill) {
    if(skill == null) return null;
    return skill.getName();
  }

  @Override
  public SlotDto convert(final Slot slot) {
    return new SlotDto(
            slot.getUuid(),
            defaultConversionService.convert(slot.getSupporter(), SupporterDto.class),
            convert(slot.getSkill())
    );
  }
}
