/**
 * 
 */
package com.harmeetsingh13.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.harmeetsingh13.entities.Person;

/**
 * @author Harmeet Singh(Taara)
 *
 */
public interface RepositoryPerson extends GraphRepository<Person>{

}
