package com.upwork.employee.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.upwork.employee.enity.Company;

@Repository
public interface CompanyRepository {

	List<Company> findAll() throws DataAccessException;
	Company findById(long empNo)throws DataAccessException;

	void save(Company employee) throws DataAccessException;
	
	Page<Company> findAll(Pageable pageable) throws DataAccessException;
	

}
