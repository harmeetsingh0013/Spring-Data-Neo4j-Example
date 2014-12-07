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
import org.springframework.data.neo4j.annotation.RelatedToVia;

import com.harmeetsingh13.entities.relationship.ActedInRelationship;
import com.harmeetsingh13.entities.relationship.FriendsRelationship;
import com.harmeetsingh13.entities.utils.RelationshipTypes;

/**
 * @author Harmeet Singh(Taara)
 *
 */
@NodeEntity
@ToString(callSuper=true, exclude={"movies", "friends"})
@EqualsAndHashCode(callSuper = true, exclude = {"name", "movies", "friends"})
public class Person extends BaseEntity{

	@Getter @Setter
	@Indexed(unique = true)
	private Long id;
	@Getter @Setter
	private String name;
	@Getter @Setter
	@RelatedToVia(type = RelationshipTypes.ACTED_IN, direction = Direction.OUTGOING, elementClass = ActedInRelationship.class)
	private Set<ActedInRelationship> movies = new HashSet<ActedInRelationship>();
	@Getter @Setter
	@RelatedToVia(direction = Direction.BOTH)
	private Set<FriendsRelationship> friends;
	
	public ActedInRelationship actedIn(Movie movie, String roleName) {
		ActedInRelationship relationship = new ActedInRelationship();
		relationship.setMovie(movie);
		relationship.setPerson(this);
		relationship.setRoles(roleName);
		this.movies.add(relationship);
		return relationship;
	}
}
