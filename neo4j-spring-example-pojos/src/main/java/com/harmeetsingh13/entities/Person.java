package com.harmeetsingh13.entities;

import java.util.HashSet;
import java.util.Set;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedToVia;

import com.harmeetsingh13.entities.utils.RelationshipTypes;

/**
 * @author Harmeet Singh(Taara)
 *
 */
@NodeEntity
@EqualsAndHashCode(callSuper = true, exclude = {"name", "movies"})
public class Person extends BaseEntity{

	@Getter @Setter
	@Indexed(unique = true)
	private Long id;
	@Getter @Setter
	private String name;
	@Getter @Setter
	@RelatedToVia(type = RelationshipTypes.ACTED_IN)
	private Set<ActedInRelationship> movies = new HashSet<ActedInRelationship>();
	
	public ActedInRelationship playIn(Movie movie, String title) {
		ActedInRelationship actedInRelationship = new ActedInRelationship();
		actedInRelationship.setRoles("Machine");
		actedInRelationship.setMovie(movie);
		actedInRelationship.setPerson(this);
		this.movies.add(actedInRelationship);
		return actedInRelationship;
	}
}
