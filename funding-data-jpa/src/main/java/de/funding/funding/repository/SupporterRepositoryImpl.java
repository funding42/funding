package de.funding.funding.repository;

import de.funding.funding.converter.SupporterToPersistentSupporterConverter;
import de.funding.funding.core.repository.SupporterRepository;
import de.funding.funding.entity.PersistentSupporter;
import de.funding.funding.entity.Supporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SupporterRepositoryImpl implements SupporterRepository {

  @Autowired
  private JpaSupporterRepository delegate;

  @Autowired
  private SupporterToPersistentSupporterConverter converter;

  @Override
  public void add(final Supporter supporter) {
    final PersistentSupporter entity = converter.convert(supporter);
    delegate.save(entity);
  }
}
