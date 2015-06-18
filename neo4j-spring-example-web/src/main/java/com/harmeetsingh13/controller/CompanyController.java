/**
 * 
 */
package com.harmeetsingh13.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.harmeetsingh13.entities.Company;
import com.harmeetsingh13.entities.Person;
import com.harmeetsingh13.service.CompanyService;

/**
 * @author Harmeet Singh(Taara)
 *
 */
@Controller
@RequestMapping(value="/")
public class CompanyController {

	@Autowired
	private CompanyService companyService;
	
	@RequestMapping(value={"create-company"}, method=RequestMethod.GET)
	public String createCompany(Model model) {
		model.addAttribute("person", new Person());
		return "person/enter-person-detail";
	}
	
	@RequestMapping(value="create-company", method=RequestMethod.POST)
	public String saveCompany(Company company, Model model) {
		Company returnCompany = companyService.createCompany(company);
		model.addAttribute("company", returnCompany);
		return "company/view-company-detail";
	}
}
