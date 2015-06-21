/**
 * 
 */
package com.harmeetsingh13.maintainrelationship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Component;

import com.harmeetsingh13.entities.Person;
import com.harmeetsingh13.entities.relationship.FriendsRelationship;

/**
 * @author Harmeet Singh(Taara)
 *
 */
@Component
public class CreateEntitiesRelationship {

	@Autowired
	private Neo4jTemplate neo4jTemplate;
	
	public FriendsRelationship createRelationshipBetweenPersons(Person person, Person friend, 
			Class<FriendsRelationship> relationshipEntity, String friendshipType) {
		FriendsRelationship relationship = neo4jTemplate.createRelationshipBetween(person, friend, relationshipEntity, friendshipType, false);
		neo4jTemplate.save(relationship);
		return relationship;
	}
}
