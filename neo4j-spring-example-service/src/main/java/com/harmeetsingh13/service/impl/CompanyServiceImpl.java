/**
 * 
 */
package com.harmeetsingh13.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harmeetsingh13.entities.Company;
import com.harmeetsingh13.repository.RepositoryCompany;
import com.harmeetsingh13.service.CompanyService;

/**
 * @author Harmeet Singh(Taara)
 *
 */
@Service
public class CompanyServiceImpl implements CompanyService{

	@Autowired
	private RepositoryCompany repositoryCompany;
	
	@Override
	public Company createCompany(Company company) {
		
		return repositoryCompany.save(company);
	}
	
	
}
