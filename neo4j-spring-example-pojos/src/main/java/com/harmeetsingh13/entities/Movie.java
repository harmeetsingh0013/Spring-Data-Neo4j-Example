/**
 * 
 */
package com.harmeetsingh13.entities;

import java.util.Set;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedToVia;

import com.harmeetsingh13.entities.utils.RelationshipTypes;

/**
 * @author Harmeet Singh(Taara)
 *
 */
@NodeEntity
@EqualsAndHashCode(callSuper=true, exclude={"title", "actors"})
@ToString(callSuper=true, includeFieldNames=true, exclude="actors")
public class Movie extends BaseEntity{
	
	@Getter @Setter
	@Indexed(unique=true)
	private Long id;
	@Getter @Setter
	private String title;
	@RelatedToVia(type = RelationshipTypes.ACTED_IN, direction = Direction.INCOMING, elementClass = ActedInRelationship.class)
	private Set<ActedInRelationship> actors;
}
