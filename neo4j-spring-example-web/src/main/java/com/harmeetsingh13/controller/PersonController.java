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

import com.harmeetsingh13.entities.Person;
import com.harmeetsingh13.entities.relationship.FriendsRelationship;
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
		model.addAttribute("person", new Person());
		return "person/enter-person-detail";
	}
	
	@RequestMapping(value="save-person", method=RequestMethod.POST)
	public String savePerson(Person person, Model model) {
		Person returnPerson = personService.save(person);
		model.addAttribute("person", returnPerson);
		return "person/view-person-detail";
	}
	
	@RequestMapping(value="find-person-by-id", method=RequestMethod.GET)
	public String findPersonById(Model model) {
		return "person/find-person-by-id";
	}
	
	@RequestMapping(value="find-person-by-id", method=RequestMethod.POST)
	public String findPersonById(long id, Model model) {
		Person person =  personService.findPersonByProperty("id", id);
		model.addAttribute("person", person);
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
		FriendsRelationship relationship = personService.makeFriends(person, friend, friendshipType);
		model.addAttribute("person", relationship.getPerson());
		model.addAttribute("personFriends", relationship.getPerson().getFriends());
		return "person/view-person-detail";
	}
	
	@RequestMapping(value="remove-person", method=RequestMethod.GET)
	public String removePerson(Model model) {
		List<Person> persons = personService.getAllPersons();
		model.addAttribute("persons", persons);
		return "person/remove-person";
	}
	
	@RequestMapping(value="remove-person", method=RequestMethod.POST)
	public String removePerson(long personId) {
		Person person = personService.findPersonByProperty("id", personId);
		personService.removePerson(person);
		return "redirect:/remove-person";
	}
	
	@RequestMapping(value="update-person-detail", method=RequestMethod.GET)
	public String updatePerson(Model model) {
		List<Person> persons = personService.getAllPersons();
		model.addAttribute("persons", persons);
		return "person/update-person";
	}
	
	@RequestMapping(value="update-person", method=RequestMethod.GET)
	public String updatePerson(long personId, Model model) {
		Person person = personService.findPersonByProperty("id", personId);
		model.addAttribute("person", person);
		return "person/update-person-detail";
	}
	
	@RequestMapping(value="update-person", method=RequestMethod.POST)
	public String updatePerson(Person person, Model model) {
		Person returnValue = personService.updatePerson(person);
		model.addAttribute("person", returnValue);
		model.addAttribute("personFriends", returnValue.getFriends());
		return "person/view-person-detail";
	}
}
