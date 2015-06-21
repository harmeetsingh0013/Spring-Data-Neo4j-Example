/**
 * 
 */
package com.harmeetsingh13.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.graphdb.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.data.neo4j.core.GraphDatabase;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
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
	@Autowired
	private GraphDatabase graphDatabase;
	
	@Override
	public Company createCompany(Company company) {
		
		return repositoryCompany.save(company);
	}

	@Override
	public List<Company> findAllCompanies() {
		List<Company> companiesList = new ArrayList<>();
		try(Transaction transaction = graphDatabase.beginTx()){
			Result<Company> companies = repositoryCompany.findAll();
			companiesList = Lists.newArrayList(companies.iterator());
			transaction.success();
		}
		return companiesList;
	}
	
	@Override
	public Company findCompanyById(long companyId){
		return repositoryCompany.findOne(companyId);
	}
	
	
}
