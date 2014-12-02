/**
 * 
 */
package com.harmeetsingh13.repositorytest;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import com.harmeetsingh13.config.Neo4jConfig;
import com.harmeetsingh13.entities.ActedInRelationship;
import com.harmeetsingh13.entities.Movie;
import com.harmeetsingh13.entities.Person;
import com.harmeetsingh13.entities.utils.RelationshipTypes;
import com.harmeetsingh13.maintainrelationship.CreateEntitiesRelationship;
import com.harmeetsingh13.repository.RepositoryMovie;

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
	private CreateEntitiesRelationship entitiesRelationship;
	
	@Test
	@Transactional
	public void persistMovie() {
		Movie movie = new Movie();
		movie.setId(13L);
		movie.setTitle("Dark Knight");
		Movie returnMovie = neo4jTemplate.save(movie);
		
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
		
		ActedInRelationship relationship = actor.actedIn(movie, RelationshipTypes.ACTED_IN);
		neo4jTemplate.save(relationship);
		
		//assertThat(returnRelationship, notNullValue());
	}
	
	//@Test
	@Transactional
	public void createRelationShipBetweenEntities() {
		Movie movie = new Movie();
		movie.setId(13L);
		movie.setTitle("Dark Knight");
		
		Person actor = new Person();
		actor.setId(9L);
		actor.setName("Harmeet Singh");
		
		ActedInRelationship relationship =  entitiesRelationship.createRelationshipBetweenPersonMovie(actor, movie, ActedInRelationship.class, RelationshipTypes.ACTED_IN);
		
		assertThat(relationship, notNullValue());
	}
}
