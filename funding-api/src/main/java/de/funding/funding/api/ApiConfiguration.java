package de.funding.funding.api;

import de.funding.funding.api.converter.ProjectStateToStateDtoConverter;
import de.funding.funding.api.converter.ProjectToProjectDtoConverter;
import de.funding.funding.api.converter.ProjectToProjectListDtoConverter;
import de.funding.funding.api.converter.SlotToSlotDtoConverter;
import de.funding.funding.api.converter.SupporterToSupporterDtoConverter;
import de.funding.funding.api.converter.UserToUserDtoConverter;
import de.funding.funding.api.converter.UserToUserOverviewDtoConverter;
import de.funding.funding.api.converter.UserTypeToTypeDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.DefaultConversionService;

import javax.annotation.PostConstruct;

@Configuration
public class ApiConfiguration {

  @Autowired
  private DefaultConversionService defaultConversionService;

  @PostConstruct
  private void postConstruct() {
    defaultConversionService.addConverter(new ProjectStateToStateDtoConverter());
    defaultConversionService.addConverter(new ProjectToProjectDtoConverter(defaultConversionService));
    defaultConversionService.addConverter(new ProjectToProjectListDtoConverter(defaultConversionService));
    defaultConversionService.addConverter(new SlotToSlotDtoConverter(defaultConversionService));
    defaultConversionService.addConverter(new SupporterToSupporterDtoConverter(defaultConversionService));
    defaultConversionService.addConverter(new UserToUserDtoConverter(defaultConversionService));
    defaultConversionService.addConverter(new UserTypeToTypeDtoConverter());
    defaultConversionService.addConverter(new UserToUserOverviewDtoConverter(defaultConversionService));
  }

}
