/**
 * 
 */
package com.harmeetsingh13.entities;

import java.util.HashSet;
import java.util.Set;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedToVia;

import com.harmeetsingh13.entities.utils.RelationshipTypes;

/**
 * @author Harmeet Singh(Taara)
 *
 */
@NodeEntity
@EqualsAndHashCode(callSuper=true, exclude={"title", "actors"})
public class Movie extends BaseEntity{
	
	@Getter @Setter
	private Long id;
	@Getter @Setter
	private String title;
	@RelatedToVia(type = RelationshipTypes.ACTED_IN)
	private Set<ActedInRelationship> actors = new HashSet<ActedInRelationship>();
}
