package com.upwork.employee.service;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.upwork.employee.enity.Company;

public interface CompanyService {

	Company findCompanyById(long companyId) throws DataAccessException;

	void saveCompany(Company company) throws DataAccessException;

	List<Company> findCompanies() throws DataAccessException;

    Page<Company> findPagedCompanies	(Pageable pageable) throws DataAccessException;
	
}
