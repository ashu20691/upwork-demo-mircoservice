package com.upwork.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.upwork.employee.enity.Company;
import com.upwork.employee.repository.CompanyRepository;

@Service
public class CompanyServiceImpl implements CompanyService{


    private static final int PAGE_SIZE = 10;

    
    private CompanyRepository companyRepository;

    
    @Autowired
    public CompanyServiceImpl(CompanyRepository CompanyRepository) {
        this.companyRepository = CompanyRepository;
    }
    @Override
    public Company findCompanyById(long empNo) throws DataAccessException {
            return companyRepository.findById(empNo);
        }
    @Override
    public List<Company> findCompanies() throws DataAccessException {
    	List<Company> results= this.companyRepository.findAll();
            return results;
        }
    @Override
    public void saveCompany(Company company) throws DataAccessException {
    	companyRepository.save(company);
        }

//    @Override
//    public void deleteCompany(Company Company) throws DataAccessException {
//            CompanyJpaRepository.delete(setEmpNo(Company));
//        }

    @Override
    public Page<Company> findPagedCompanies(Pageable pg) throws DataAccessException {
    	  Page<Company> results= this.companyRepository.findAll(pg);
    	   return results;
    }
}
