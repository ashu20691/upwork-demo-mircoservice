package com.upwork.employee.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.upwork.employee.enity.City;

@Repository
public interface CityRepository {

	List<City> findAll() throws DataAccessException;
	City findById(long cityId)throws DataAccessException;

	void save(City city) throws DataAccessException;
	
	Page<City> findAll(Pageable pageable) throws DataAccessException;
	

}
