/**
 * 
 */
package com.harmeetsingh13.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.harmeetsingh13.entities.Person;
import com.harmeetsingh13.service.PersonService;

/**
 * @author Harmeet Singh(Taara)
 *
 */
@Controller
@RequestMapping(value="/")
public class PersonController {

	@Autowired
	private PersonService personService;
	
	@RequestMapping(value="save-person", method=RequestMethod.GET)
	public String savePerson(Model model) {
		model.addAttribute("actor", new Person());
		return "person/enter-person-detail";
	}
	
	@RequestMapping(value="save-person", method=RequestMethod.POST)
	public String savePerson(Person person, Model model) {
		Person returnPerson = personService.save(person);
		model.addAttribute("actor", returnPerson);
		return "person/view-person-detail";
	}
	
	@RequestMapping(value="find-person-by-id", method=RequestMethod.GET)
	public String findPersonById(Model model) {
		return "person/find-person-by-id";
	}
	
	@RequestMapping(value="find-person-by-id", method=RequestMethod.POST)
	public String findPersonById(long id, Model model) {
		Person person =  personService.findPersonByProperty("id", id);
		model.addAttribute("actor", person);
		model.addAttribute("personFriends", person.getFriends());
		return "person/view-person-detail";
	}
	
	@RequestMapping(value="make-friends", method=RequestMethod.GET)
	public String makeFriends(Model model){
		List<Person> persons = personService.getAllPersons();
		model.addAttribute("persons", persons);
		return "person/make-friends";
	}
	
	@RequestMapping(value="make-friends", method=RequestMethod.POST)
	public String makeFriends(long personId, long friendId, String friendshipType, Model model) {
		Person person = personService.findPersonByProperty("id", personId);
		Person friend = personService.findPersonByProperty("id", friendId);
		personService.makeFriends(person, friend, friendshipType);
		model.addAttribute("actor", person);
		model.addAttribute("personFriends", person.getFriends());
		return "person/view-person-detail";
	}
}
