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

import com.upwork.employee.enity.City;
import com.upwork.employee.service.CityService;


@Controller
@RequestMapping("/welcome1")
public class CityController {

    @Autowired
    private CityService cityService;
    

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String goHome(){ 
       return "home";
    } 
    
    @RequestMapping(value ="/cities/pages", method = RequestMethod.GET)
    public String showPages(Model model,@PageableDefault(page = 0,size = 20,direction = Direction.ASC, sort = {"cityId"}) Pageable pg) {
   	 Page<City> results = this.cityService.findPagedCities(pg);
   	 model.addAttribute("listcity", results);
   	 return "/cities/page";
    }
    
    @RequestMapping(value = "/cities", method = RequestMethod.GET)
    public ModelAndView getCitiesList(){ 
    	List<City> results = this.cityService.findCities();
        ModelAndView model1 = new ModelAndView("/cities/list");
        model1.addObject("listcity", results);
        return model1;
    }
    
    @RequestMapping(value = "/cities/add", method = RequestMethod.GET)
    public ModelAndView addForm()
    {
        return new ModelAndView("cities/add", "city", new City());
    }
    
    @RequestMapping(value = "/cities/save", method = RequestMethod.POST)
    public String processAdd(@Valid @ModelAttribute("city")City city, 
    	      BindingResult result, ModelMap model) {
    	if(result.hasErrors()) {
            return "cities/add";
    	}
         this.cityService.saveCity(city);
         return "redirect:/welcome1/cities";
    }
    
    @RequestMapping(value = "/cities/edit/{cityId}", method = RequestMethod.GET)
    public String editForm(@PathVariable("cityId") long cityId, Model model){
    	City city=this.cityService.findCityById(cityId); 
        model.addAttribute(city);
        return "cities/edit";
    }

    @RequestMapping(value = "/cities/update",method = RequestMethod.POST)
    public String processUpdate(@Valid @ModelAttribute("city")City city, 
  	      BindingResult result, ModelMap model){
    	if(result.hasErrors()) {
            return "cities/edit";
    	}
        this.cityService.saveCity(city);
        return "redirect:/welcome1/cities";
    }
    
    @RequestMapping(value = "/cities/find", method = RequestMethod.GET)
    public String getCity(@RequestParam("citId") long cityId, Model model){
        City city1=this.cityService.findCityById(cityId); 
        model.addAttribute(city1);
        return "cities/find";
    }
}