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
import com.harmeetsingh13.entities.Person;
import com.harmeetsingh13.entities.utils.RelationshipTypes;

/**
 * @author james
 *
 */
@RelationshipEntity(type=RelationshipTypes.FRIEND)
public class FriendsRelationship extends BaseEntity{

	@StartNode
	@Getter @Setter
	private Person person;
	@EndNode
	@Getter @Setter
	private Person friend;
	@Getter @Setter
	private String friendsType;
}
