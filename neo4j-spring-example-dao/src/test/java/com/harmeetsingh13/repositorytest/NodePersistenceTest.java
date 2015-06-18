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
import com.harmeetsingh13.entities.Company;
import com.harmeetsingh13.entities.Person;
import com.harmeetsingh13.entities.relationship.EmployeRelationship;
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
		Company company = new Company();
		company.setId(15L);
		company.setName("Dark Knight");
		Company returnMovie = movieRepo.save(company);
		
		Company findMovie = movieRepo.findOne(returnMovie.getGraphId());
		System.out.println(findMovie);
		assertThat(findMovie, notNullValue());
	}
	
	@Test
	@Transactional
	public void createSimpleRelationShip() {
		Company company = new Company();
		company.setId(13L);
		company.setName("Dark Knight");
		
		Person actor = new Person();
		actor.setId(9L);
		actor.setName("Harmeet Singh");
		
		/* For create relationship between nodes, firstly we need to persist both nodes, because when we persist the relationship entity 
		 * internally, they fetch start-node and end-node from its persistence state. when we are not persist the nodes, it will throw an 
		 * NullPointerException . When we persist the relationship entity internally they use following method
		 * Relationship	createRelationshipBetween(Node startNode, Node endNode, String relationshipType, Map<String,Object> properties)*/
		neo4jTemplate.save(company);
		neo4jTemplate.save(actor);
		
		EmployeRelationship relationship = actor.employedAt(company, RelationshipTypes.EMPLOYED_IN);
		neo4jTemplate.save(relationship);
		
		//assertThat(returnRelationship, notNullValue());
	}
	
	@Test
	@Transactional
	public void createRelationShipUsingServiceLayer() {
		Company company = new Company();
		company.setId(13L);
		company.setName("Dark Knight");
		
		Person actor = new Person();
		actor.setId(9L);
		actor.setName("Harmeet Singh");
		
		neo4jTemplate.save(company);
		neo4jTemplate.save(actor);
		
		//EmployeRelationship relationship =  entitiesRelationship.createRelationshipBetweenPersonMovie(actor, company, EmployeRelationship.class, RelationshipTypes.EMPLOYED_IN);
		
		//assertThat(relationship, notNullValue());
	}
	
	@Test
	@Transactional
	public void findAllNodes(){
		Result<Person> persons = actorRepo.findAll(); 
		persons.forEach(person -> System.out.println(person));
	}
}
