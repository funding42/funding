package de.funding.funding.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import de.funding.funding.entity.PersistentSlot;
import de.funding.funding.entity.Slot;

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
		if (source.getSupporter() != null) {
			result.setSupporter(supporterConverter.convert(source.getSupporter()));
		}
		return result;
	}
}
