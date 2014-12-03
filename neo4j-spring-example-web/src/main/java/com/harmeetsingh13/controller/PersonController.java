/**
 * 
 */
package com.harmeetsingh13.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
		return "person/person-detail";
	}
}
