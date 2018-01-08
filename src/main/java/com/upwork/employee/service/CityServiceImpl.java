package com.upwork.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.upwork.employee.enity.City;
import com.upwork.employee.repository.CityRepository;

@Service
public class CityServiceImpl implements CityService{


    private static final int PAGE_SIZE = 10;

    
    private CityRepository cityRepository;

    
    @Autowired
    public CityServiceImpl(CityRepository CityRepository) {
        this.cityRepository = CityRepository;
    }
    @Override
    public City findCityById(long empNo) throws DataAccessException {
            return cityRepository.findById(empNo);
        }
    @Override
    public List<City> findCities() throws DataAccessException {
    	List<City> results= this.cityRepository.findAll();
            return results;
        }
    @Override
    public void saveCity(City city) throws DataAccessException {
    	cityRepository.save(city);
        }

//    @Override
//    public void deleteCity(City City) throws DataAccessException {
//            CityJpaRepository.delete(setEmpNo(City));
//        }

    @Override
    public Page<City> findPagedCities(Pageable pg) throws DataAccessException {
    	  Page<City> results= this.cityRepository.findAll(pg);
    	   return results;
    }
}
