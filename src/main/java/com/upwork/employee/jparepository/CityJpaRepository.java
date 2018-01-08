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

import com.upwork.employee.enity.City;
import com.upwork.employee.repository.CityRepository;

@Repository
public interface CityJpaRepository extends CityRepository, PagingAndSortingRepository<City,Long> {

	List<City> findAll() throws DataAccessException;

	@Query("SELECT e FROM City e WHERE e.cityId =:cityId")
	City findById(@Param("cityId") long cityId); 

	Page<City> findAll(Pageable pageable) throws DataAccessException;
}
