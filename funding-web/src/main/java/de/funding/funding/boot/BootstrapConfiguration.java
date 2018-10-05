package de.funding.funding.boot;

import de.funding.funding.core.boot.DataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.util.List;

@Configuration
public class BootstrapConfiguration {

  private final List<DataProvider> providers;

  @Autowired(required = false)
  public BootstrapConfiguration(final List<DataProvider> providers) {
    this.providers = providers;
  }

  @EventListener(ApplicationReadyEvent.class)
  public void init() {
    providers.forEach(DataProvider::load);
  }
}
