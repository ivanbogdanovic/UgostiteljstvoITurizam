package com.oglasi.controller;

import com.oglasi.model.*;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SiteController {

    @Autowired
    JobDao jobDao;
    
    @Autowired
    CountryDao countryDao;
    
    @Autowired
    CityDao cityDao;
    
    @Autowired
    JobPositionDao jobPositionDao;
    
    @Autowired
    CompanyTypeDao companyTypeDao;
    
    @Autowired
    EmploymentTypeDao employmentTypeDao;
    
    @Autowired
    CompanyDao companyDao;
    
    
    @RequestMapping(value="/")
    public String index(
            @RequestParam(required = false, defaultValue = "1") Short cnt,
            @RequestParam(required = false) Integer city_search,
            @RequestParam(required = false) Short company_type,
            @RequestParam(required = false) Integer job_position,
            @RequestParam(required = false) Integer employment_type,
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer per_page,
            @RequestParam(required = false) Integer msg,
            HttpServletRequest request,
            ModelMap model){
        
        List<Job> job = jobDao.jobSearchMain(cnt, city_search, company_type, job_position, employment_type, page, per_page);
        List<Country> country = countryDao.findAll();
        List<City> city = cityDao.findByCountry(cnt);
        List<JobPosition> jobPosition = jobPositionDao.findAll();
        List<CompanyType> companyType = companyTypeDao.findAll();
        List<EmploymentType> employmentType = employmentTypeDao.findAll();
        HttpSession session = request.getSession();
        model.addAttribute("totalPages",jobDao.numOfPages);
        model.addAttribute("job", job);
        model.addAttribute("country", country);
        model.addAttribute("city", city);
        model.addAttribute("jobPosition", jobPosition);
        model.addAttribute("companyType", companyType);
        model.addAttribute("employmentType", employmentType);
        if(session.getAttribute("compId")!=null){
           Company comp = companyDao.findById(Short.toUnsignedInt((Short)session.getAttribute("compId")));
           model.addAttribute("comp", comp);
        }
        if(msg!=null && msg==3){
           model.addAttribute("message", "morate biti ulogovani"); 
        }   
        return "index";
    }
    
    
    @RequestMapping("/job/{id}")
    public String job(@PathVariable int id, ModelMap model, HttpServletRequest request){
        HttpSession session = request.getSession();
        Job job = jobDao.findById(id);
        model.addAttribute("job", job);
        model.addAttribute("expirationDate", jobDao.expirationDate(id));
        if(session.getAttribute("compId")!=null){
           Company comp = companyDao.findById(Short.toUnsignedInt((Short)session.getAttribute("compId")));
           model.addAttribute("comp", comp);
        }
        return "job";
    }
    
    
    @RequestMapping("/company_info/{id}")
    public String companyInfo(@PathVariable Short id, ModelMap model, HttpServletRequest request){
        HttpSession session = request.getSession();
        Company company = companyDao.findById(id);
        List<Job> job = jobDao.findByCompAndStatus(id, Status.ACTIVE);
        model.addAttribute("company", company);
        model.addAttribute("job", job);
         if(session.getAttribute("compId")!=null){
           Company comp = companyDao.findById(Short.toUnsignedInt((Short)session.getAttribute("compId")));
           model.addAttribute("comp", comp);
        }
        return "company_info";
    }
    
    
    @RequestMapping("/sign_out")
    public void signOut(HttpServletRequest request, HttpServletResponse response) throws IOException{
        HttpSession session = request.getSession();
        session.removeAttribute("compId");
        response.sendRedirect("");
    }
}
