package de.funding.funding.bootstrap;

import de.funding.funding.core.bootstrap.InitialDataProvider;
import org.springframework.stereotype.Service;

@Service
public class DemoDataProvider implements InitialDataProvider {
  @Override
  public void loadData() {
    System.out.println();
  }
}
