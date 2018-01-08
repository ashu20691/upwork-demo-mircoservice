package com.upwork.employee.service;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.upwork.employee.enity.City;

public interface CityService {

	City findCityById(long cityId) throws DataAccessException;

	void saveCity(City city) throws DataAccessException;

	List<City> findCities() throws DataAccessException;

    Page<City> findPagedCities	(Pageable pageable) throws DataAccessException;
	
}
