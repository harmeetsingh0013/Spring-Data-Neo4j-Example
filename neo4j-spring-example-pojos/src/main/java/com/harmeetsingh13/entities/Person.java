package com.harmeetsingh13.entities;

import java.util.HashSet;
import java.util.Set;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.annotation.RelatedToVia;

import com.harmeetsingh13.entities.relationship.EmployeRelationship;
import com.harmeetsingh13.entities.relationship.FriendsRelationship;
import com.harmeetsingh13.entities.utils.RelationshipTypes;

/**
 * @author Harmeet Singh(Taara)
 *
 */
@NodeEntity
@ToString(callSuper=true, exclude={"company", "friends"})
@EqualsAndHashCode(callSuper = true, exclude = {"name", "company", "friends"})
public class Person extends BaseEntity{

	@Getter @Setter
	@Indexed(unique = true)
	private Long id;
	@Getter @Setter
	private String name;
	
	@Getter @Setter
	@RelatedTo(type = RelationshipTypes.EMPLOYED_IN)
	private Set<Company> company = new HashSet<Company>();
	
	@Getter @Setter
	@RelatedToVia(direction = Direction.OUTGOING, type = RelationshipTypes.FRIEND)
	private Set<FriendsRelationship> friends;
	
	public EmployeRelationship employedAt(Company company, String roleName) {
		EmployeRelationship relationship = new EmployeRelationship();
		relationship.setCompany(company);
		relationship.setPerson(this);
		relationship.setRole(roleName);
		this.company.add(company);
		return relationship;
	}
}
