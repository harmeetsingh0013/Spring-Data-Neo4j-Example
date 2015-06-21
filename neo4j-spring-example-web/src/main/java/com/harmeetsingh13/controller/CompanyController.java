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

import com.harmeetsingh13.entities.Company;
import com.harmeetsingh13.entities.Person;
import com.harmeetsingh13.service.CompanyService;
import com.harmeetsingh13.service.PersonService;

/**
 * @author Harmeet Singh(Taara)
 *
 */
@Controller
@RequestMapping(value="/")
public class CompanyController {

	@Autowired
	private CompanyService companyService;
	@Autowired
	private PersonService personService;
	
	@RequestMapping(value={"create-company"}, method=RequestMethod.GET)
	public String createCompany(Model model) {
		model.addAttribute("company", new Person());
		return "company/create-company";
	}
	
	@RequestMapping(value="create-company", method=RequestMethod.POST)
	public String saveCompany(Company company, Model model) {
		Company returnCompany = companyService.createCompany(company);
		model.addAttribute("company", returnCompany);
		return "company/view-company-detail";
	}
	
	@RequestMapping(value="make-employee", method=RequestMethod.GET)
	public String makeEmployee(Model model) {
		List<Person> persons = personService.getAllPersons();
		List<Company> companies = companyService.findAllCompanies();
		model.addAttribute("companies", companies);
		model.addAttribute("persons", persons);
		return "company/make-employee";
	}
	
	@RequestMapping(value="make-employee", method=RequestMethod.POST)
	public String makeEmployee(Model model, long empId, long companyId) {
		Company company = companyService.findCompanyById(companyId);
		Person person = personService.findPersonByProperty("id", empId);
		personService.employedAt(company, person, "Employee");
		personService.updatePerson(person);
		company = companyService.findCompanyById(companyId);
		model.addAttribute("company", company);
		model.addAttribute("employees", company.getEmployes());
		return "company/view-company-detail";
	}
}
