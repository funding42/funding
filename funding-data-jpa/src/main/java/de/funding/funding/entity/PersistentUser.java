package de.funding.funding.entity;

import static javax.persistence.EnumType.STRING;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "FUN_User")
@SuppressWarnings("squid:TrailingCommentCheck")
public class PersistentUser extends AbstractPersistentEntity {

	private String firstName;

	private String lastName;

	private String description;

	private UserType type;

	private Set<PersistentSkill> skills;

	@Column(name = "first_name", nullable = false)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name", nullable = false)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@Enumerated(STRING)
	@Column(name = "user_type", nullable = false)
	public UserType getType() {
		return type;
	}

	public void setType(final UserType type) {
		this.type = type;
	}

	@ManyToMany(targetEntity = PersistentSkill.class, fetch = FetchType.EAGER)
	@JoinTable(name = "FUN_User_Skill", joinColumns = { //
			@JoinColumn(name = "user_id", nullable = false) //
	}, foreignKey = @ForeignKey(name = "FK_FUN_User_Skill_user"), //
	inverseJoinColumns = { //
			@JoinColumn(name = "skill_id", nullable = false) //
	}, inverseForeignKey = @ForeignKey(name = "FK_FUN_User_Skill_skill"))
	public Set<PersistentSkill> getSkills() {
		return skills;
	}

	public void setSkills(final Set<PersistentSkill> skills) {
		this.skills = skills;
	}
}
