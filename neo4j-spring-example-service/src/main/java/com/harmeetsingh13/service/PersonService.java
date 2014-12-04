/**
 * 
 */
package com.harmeetsingh13.service;

import com.harmeetsingh13.entities.Person;

/**
 * @author Harmeet Singh(Taara)
 *
 */
public interface PersonService{
	
	public Person save(Person person);
	public Person findPersonByProperty(String property, Object value);
}
