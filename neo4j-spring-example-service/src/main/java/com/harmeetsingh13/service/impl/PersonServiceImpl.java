/**
 * 
 */
package com.harmeetsingh13.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harmeetsingh13.entities.Person;
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
	
	@Override
	public Person save(Person entity) {
		return repositoryPerson.save(entity);
	}

	@Override
	public Person findPersonByProperty(String property, Object value) {
		return repositoryPerson.findBySchemaPropertyValue(property, value);
	}
}
