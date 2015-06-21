/**
 * 
 */
package com.harmeetsingh13.service;

import java.util.List;

import com.harmeetsingh13.entities.Company;

/**
 * @author Harmeet Singh(Taara)
 *
 */
public interface CompanyService{
	
	public Company createCompany(Company company);
	public List<Company> findAllCompanies();
	public Company findCompanyById(long companyId);
}
