package com.upwork.employee.jparepository;

//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.upwork.employee.enity.Employee;
import com.upwork.employee.repository.EmployeeRepository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface EmployeeJpaRepository extends EmployeeRepository, PagingAndSortingRepository<Employee,Long> {

	List<Employee> findAll() throws DataAccessException;

	@Query("SELECT e FROM Employee e WHERE e.empNo =:empNo")
	Employee findById(@Param("empNo") long empNo); 

	Page<Employee> findAll(Pageable pageable) throws DataAccessException;
}
