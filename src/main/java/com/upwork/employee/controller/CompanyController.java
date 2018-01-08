package com.upwork.employee.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.upwork.employee.enity.Company;
import com.upwork.employee.service.CompanyService;


@Controller
@RequestMapping("/welcome2")
public class CompanyController {

    @Autowired
    private CompanyService companyService;
    

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String goHome(){ 
       return "home";
    } 
    
    @RequestMapping(value ="/companies/pages", method = RequestMethod.GET)
    public String showPages(Model model,@PageableDefault(page = 0,size = 20,direction = Direction.ASC, sort = {"companyId"}) Pageable pg) {
   	 Page<Company> results = this.companyService.findPagedCompanies(pg);
   	 model.addAttribute("listcompany", results);
   	 return "/companies/page";
    }
    
    @RequestMapping(value = "/companies", method = RequestMethod.GET)
    public ModelAndView getCompaniesList(){ 
    	List<Company> results = this.companyService.findCompanies();
        ModelAndView model1 = new ModelAndView("/companies/list");
        model1.addObject("listcompany", results);
        return model1;
    }
    
    @RequestMapping(value = "/companies/add", method = RequestMethod.GET)
    public ModelAndView addForm()
    {
        return new ModelAndView("companies/add", "company", new Company());
    }
    
    @RequestMapping(value = "/companies/save", method = RequestMethod.POST)
    public String processAdd(@Valid @ModelAttribute("company")Company company, 
    	      BindingResult result, ModelMap model) {
    	if(result.hasErrors()) {
            return "companies/add";
    	}
         this.companyService.saveCompany(company);
         return "redirect:/welcome2/companies";
    }
    
    @RequestMapping(value = "/companies/edit/{companyId}", method = RequestMethod.GET)
    public String editForm(@PathVariable("companyId") long companyId, Model model){
    	Company company=this.companyService.findCompanyById(companyId); 
        model.addAttribute(company);
        return "companies/edit";
    }

    @RequestMapping(value = "/companies/update",method = RequestMethod.POST)
    public String processUpdate(@Valid @ModelAttribute("company")Company company, 
  	      BindingResult result, ModelMap model){
    	if(result.hasErrors()) {
            return "companies/edit";
    	}
        this.companyService.saveCompany(company);
        return "redirect:/welcome2/companies";
    }
    
    @RequestMapping(value = "/companies/find", method = RequestMethod.GET)
    public String getCompany(@RequestParam("citId") long companyId, Model model){
        Company company1=this.companyService.findCompanyById(companyId); 
        model.addAttribute(company1);
        return "companies/find";
    }
}