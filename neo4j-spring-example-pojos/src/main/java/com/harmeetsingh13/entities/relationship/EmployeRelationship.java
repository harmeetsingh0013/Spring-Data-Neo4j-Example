/**
 * 
 */
package com.harmeetsingh13.entities.relationship;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

import com.harmeetsingh13.entities.BaseEntity;
import com.harmeetsingh13.entities.Company;
import com.harmeetsingh13.entities.Person;
import com.harmeetsingh13.entities.utils.RelationshipTypes;

/**
 * @author Harmeet Singh(Taara)
 *
 */
@RelationshipEntity(type = RelationshipTypes.EMPLOYED_IN)
public class EmployeRelationship extends BaseEntity {

	@StartNode
	@Getter @Setter
	private Person person;
	@EndNode
	@Getter @Setter
	private Company company;
	@Getter @Setter
	private String role;
}
