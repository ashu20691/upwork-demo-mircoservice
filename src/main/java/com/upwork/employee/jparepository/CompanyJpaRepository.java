package com.upwork.employee.jparepository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.upwork.employee.enity.Company;
import com.upwork.employee.repository.CompanyRepository;

@Repository
public interface CompanyJpaRepository extends CompanyRepository, PagingAndSortingRepository<Company,Long> {

	List<Company> findAll() throws DataAccessException;

	@Query("SELECT e FROM Company e WHERE e.companyId =:companyId")
	Company findById(@Param("companyId") long companyId); 

	Page<Company> findAll(Pageable pageable) throws DataAccessException;
}
