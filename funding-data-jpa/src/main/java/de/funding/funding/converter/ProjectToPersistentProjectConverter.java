package de.funding.funding.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import de.funding.funding.entity.PersistentProject;
import de.funding.funding.entity.Project;

@Component
public class ProjectToPersistentProjectConverter implements Converter<Project, PersistentProject> {

	@Autowired
	private UserToPersistentConverter userToPersistentUserConverter;

	@Autowired
	private LocationToEmbeddedLocationConverter locationToPersistentLocationConverter;

	@Override
	public PersistentProject convert(final Project source) {
		final PersistentProject target = new PersistentProject();
		target.setCreator(userToPersistentUserConverter.convert(source.getCreator()));
		target.setDescription(source.getDescription());
		target.setFundingGoal(source.getInvestmentGoal());
		target.setId(source.getUuid());
		target.setLocation(locationToPersistentLocationConverter.convert(source.getLocation()));
		target.setState(source.getState());
		target.setTeaser(source.getTeaser());
		target.setTitle(source.getTitle());
		target.setCreatedAt(source.getCreatedAt());
		target.setUpdatedAt(source.getLastModified());
		return target;
	}
}
