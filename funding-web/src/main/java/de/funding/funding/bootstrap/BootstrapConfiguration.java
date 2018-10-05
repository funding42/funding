package de.funding.funding.bootstrap;

import de.funding.funding.core.bootstrap.InitialDataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.util.List;

@Configuration
public class BootstrapConfiguration {

  @Autowired(required = false)
  private List<InitialDataProvider> providers;

  @EventListener(ApplicationReadyEvent.class)
  public void executeDataProviders() {
    if(providers != null) {
      for(InitialDataProvider provider: providers) {
        provider.loadData();
      }
    }
  }
}
