/**
 * 
 */
package com.harmeetsingh13.entities;

import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

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
@EqualsAndHashCode(callSuper=true, exclude={"name", "employes"})
@ToString(callSuper=true, includeFieldNames=true, exclude="employes")
public class Company extends BaseEntity{
	
	@Getter @Setter
	@Indexed(unique=true)
	private Long id;
	@Getter @Setter
	private String name;
	@Getter @Setter
	@RelatedTo(type=RelationshipTypes.EMPLOYED_IN, direction=Direction.INCOMING)
	private Set<Person> employes;
}
