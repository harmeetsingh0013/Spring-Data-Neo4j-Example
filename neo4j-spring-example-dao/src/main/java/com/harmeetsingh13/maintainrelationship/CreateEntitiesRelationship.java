/**
 * 
 */
package com.harmeetsingh13.maintainrelationship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Component;

import com.harmeetsingh13.entities.ActedInRelationship;
import com.harmeetsingh13.entities.Movie;
import com.harmeetsingh13.entities.Person;

/**
 * @author Harmeet Singh(Taara)
 *
 */
@Component
public class CreateEntitiesRelationship {

	@Autowired
	private Neo4jTemplate neo4jTemplate;
	
	public ActedInRelationship createRelationshipBetweenPersonMovie(Person person, Movie movie, 
			Class<ActedInRelationship> relationshipEntity, String relationshipType){
		return neo4jTemplate.createRelationshipBetween(person, movie, relationshipEntity, relationshipType, false);
	}
}
