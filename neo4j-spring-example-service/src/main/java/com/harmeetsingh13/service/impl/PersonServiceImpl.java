/**
 * 
 */
package com.harmeetsingh13.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.graphdb.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.data.neo4j.core.GraphDatabase;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.harmeetsingh13.entities.Person;
import com.harmeetsingh13.entities.relationship.FriendsRelationship;
import com.harmeetsingh13.maintainrelationship.CreateEntitiesRelationship;
import com.harmeetsingh13.repository.RepositoryPerson;
import com.harmeetsingh13.service.PersonService;

/**
 * @author Harmeet Singh(Taara)
 *
 */
@Service
public class PersonServiceImpl implements PersonService{

	@Autowired
	private RepositoryPerson repositoryPerson;
	@Autowired
	private Neo4jTemplate template;
	@Autowired
	private GraphDatabase graphDatabase;
	@Autowired
	private CreateEntitiesRelationship maintainRelationship;
	
	@Override
	public Person save(Person entity) {
		return repositoryPerson.save(entity);
	}

	@Override
	public Person findPersonByProperty(String property, Object value) {
		return repositoryPerson.findBySchemaPropertyValue(property, value);
	}

	/** In the embedded base neo4j database the findAll() method throw an exception:
	 * java.lang.NullPointerException
		at org.neo4j.kernel.TopLevelTransaction.markAsRollbackOnly(TopLevelTransaction.java:93)
		at org.neo4j.kernel.TopLevelTransaction.failure(TopLevelTransaction.java:86)
	 * Because of some transaction behaviour. When apply the @Transaction annotation on the service layer 
	 * still creating the same issue. 
	 * So we need to manage transaction manually as below. If we use external database, i am not sure, this exception
	 * is fixed, but first try to use @Transactional annotation
	 */
	@Override
	public List<Person> getAllPersons() {
		List<Person> personsList = new ArrayList<>();
		try(Transaction transaction = graphDatabase.beginTx()){
			Result<Person> persons = repositoryPerson.findAll();
			personsList = Lists.newArrayList(persons.iterator());
			transaction.success();
		}
		return personsList;
	}

	@Override
	public FriendsRelationship makeFriends(Person person, Person friend, String friendshipType) {
		FriendsRelationship relationship = null;
		try(Transaction transaction = graphDatabase.beginTx()){
			relationship = maintainRelationship.createRelationshipBetweenPersons(person, friend, FriendsRelationship.class, friendshipType);
			transaction.success();
		}
		return relationship;
	}
}
