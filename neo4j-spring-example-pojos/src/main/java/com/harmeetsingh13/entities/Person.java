package com.harmeetsingh13.entities;

import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.annotation.RelatedToVia;

import com.harmeetsingh13.entities.relationship.FriendsRelationship;
import com.harmeetsingh13.entities.utils.RelationshipTypes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
	@RelatedTo(type = RelationshipTypes.EMPLOYED_IN, direction = Direction.OUTGOING)
	private Company company;
	
	@Getter @Setter
	@RelatedToVia(direction = Direction.OUTGOING, type = RelationshipTypes.FRIEND)
	private Set<FriendsRelationship> friends;
}
