/**
 * 
 */
package com.harmeetsingh13.repositorytest;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import com.harmeetsingh13.config.Neo4jConfig;
import com.harmeetsingh13.entities.Movie;
import com.harmeetsingh13.entities.Person;
import com.harmeetsingh13.entities.relationship.ActedInRelationship;
import com.harmeetsingh13.entities.utils.RelationshipTypes;
import com.harmeetsingh13.maintainrelationship.CreateEntitiesRelationship;
import com.harmeetsingh13.repository.RepositoryMovie;
import com.harmeetsingh13.repository.RepositoryPerson;

/**
 * @author Harmeet Singh(Taara)
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Neo4jConfig.class}, loader = AnnotationConfigContextLoader.class)
public class NodePersistenceTest {

	@Autowired
	private Neo4jTemplate neo4jTemplate;
	@Autowired
	private RepositoryMovie movieRepo;
	@Autowired
	private RepositoryPerson actorRepo;
	@Autowired
	private CreateEntitiesRelationship entitiesRelationship;
	
	@Test
	@Transactional
	public void persistMovie() {
		Movie movie = new Movie();
		movie.setId(15L);
		movie.setTitle("Dark Knight");
		Movie returnMovie = movieRepo.save(movie);
		
		Movie findMovie = movieRepo.findOne(returnMovie.getGraphId());
		System.out.println(findMovie);
		assertThat(findMovie, notNullValue());
	}
	
	@Test
	@Transactional
	public void createSimpleRelationShip() {
		Movie movie = new Movie();
		movie.setId(13L);
		movie.setTitle("Dark Knight");
		
		Person actor = new Person();
		actor.setId(9L);
		actor.setName("Harmeet Singh");
		
		/* For create relationship between nodes, firstly we need to persist both nodes, because when we persist the relationship entity 
		 * internally, they fetch start-node and end-node from its persistence state. when we are not persist the nodes, it will throw an 
		 * NullPointerException . When we persist the relationship entity internally they use following method
		 * Relationship	createRelationshipBetween(Node startNode, Node endNode, String relationshipType, Map<String,Object> properties)*/
		neo4jTemplate.save(movie);
		neo4jTemplate.save(actor);
		
		ActedInRelationship relationship = actor.actedIn(movie, RelationshipTypes.ACTED_IN);
		neo4jTemplate.save(relationship);
		
		//assertThat(returnRelationship, notNullValue());
	}
	
	@Test
	@Transactional
	public void createRelationShipUsingServiceLayer() {
		Movie movie = new Movie();
		movie.setId(13L);
		movie.setTitle("Dark Knight");
		
		Person actor = new Person();
		actor.setId(9L);
		actor.setName("Harmeet Singh");
		
		neo4jTemplate.save(movie);
		neo4jTemplate.save(actor);
		
		ActedInRelationship relationship =  entitiesRelationship.createRelationshipBetweenPersonMovie(actor, movie, ActedInRelationship.class, RelationshipTypes.ACTED_IN);
		
		assertThat(relationship, notNullValue());
	}
	
	@Test
	@Transactional
	public void findAllNodes(){
		Result<Person> persons = actorRepo.findAll(); 
		persons.forEach(person -> System.out.println(person));
	}
}
