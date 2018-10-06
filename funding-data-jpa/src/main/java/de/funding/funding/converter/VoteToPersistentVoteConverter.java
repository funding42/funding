package de.funding.funding.converter;

import de.funding.funding.entity.PersistentVote;
import de.funding.funding.entity.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class VoteToPersistentVoteConverter implements Converter<Vote, PersistentVote> {

  @Autowired
  private ProjectToPersistentProjectConverter projectConverter;

  @Autowired
  private UserToPersistentConverter userConverter;

  @Override
  public PersistentVote convert(final Vote source) {
    PersistentVote result = new PersistentVote();
    result.setId(source.getUuid());
    result.setProject(projectConverter.convert(source.getProject()));
    result.setUser(userConverter.convert(source.getUser()));
    result.setUpvote(source.isUpvote());
    result.setCreatedAt(source.getCreationDate());
    return result;
  }
}
