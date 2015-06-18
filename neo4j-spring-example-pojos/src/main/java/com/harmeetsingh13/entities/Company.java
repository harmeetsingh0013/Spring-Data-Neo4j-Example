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

import com.harmeetsingh13.entities.relationship.EmployeRelationship;
import com.harmeetsingh13.entities.utils.RelationshipTypes;

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
	
	@RelatedToVia(type = RelationshipTypes.EMPLOYED_IN, direction = Direction.INCOMING, elementClass = EmployeRelationship.class)
	private Set<EmployeRelationship> employes;
}
