package de.funding.funding.boot;

import de.funding.funding.core.boot.DataProvider;
import org.springframework.stereotype.Service;

@Service
public class DemoDataProvider implements DataProvider {

  @Override
  public void load() {
    System.out.println();
  }
}
