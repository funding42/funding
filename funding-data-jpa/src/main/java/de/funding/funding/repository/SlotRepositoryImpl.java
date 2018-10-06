package de.funding.funding.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.funding.funding.converter.SlotToPersistentSlotConverter;
import de.funding.funding.core.repository.SlotRepository;
import de.funding.funding.entity.Slot;

@Component
public class SlotRepositoryImpl implements SlotRepository {

	@Autowired
	private JpaSlotRepository delegate;

	@Autowired
	private SlotToPersistentSlotConverter slotToPersistentSlotConverter;

	@Override
	public void add(final Slot slot) {
		delegate.save(slotToPersistentSlotConverter.convert(slot));
	}

}
