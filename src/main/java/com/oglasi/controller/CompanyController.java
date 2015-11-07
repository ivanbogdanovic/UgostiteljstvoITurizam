package com.oglasi.controller;

import com.oglasi.model.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("company")
public class CompanyController {

    @Autowired
    CityDao cityDao;
    
    @Autowired
    CompanyTypeDao companyTypeDao;
    
    @Autowired
    CompanyDao companyDao;
    
    @Autowired
    JobPositionDao jobPositionDao;
    
    @Autowired
    EmploymentTypeDao employmentTypeDao;
    
    @Autowired
    JobDao jobDao;
    
    
    @RequestMapping(value="/sign_up_form", method = RequestMethod.GET)
    public String signUpForm(ModelMap model){
        List<CompanyType> companyType = companyTypeDao.findAll();
        List<City> city = cityDao.findAll();
        model.addAttribute("companyType", companyType);
        model.addAttribute("city", city);
        return "company/sign_up_form";
    }
    
    
    @RequestMapping(value="/sign_up_form", method = RequestMethod.POST)
    public String signUpFormPost(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) Integer cityId,
            @RequestParam(required = false) Short companyTypeId,
            @RequestParam(required = false) String web,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String fax,
            @RequestParam(required = false) String mat_br,
            @RequestParam(required = false) String pib,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) String about,
            @RequestParam(required = false) MultipartFile logo,
            //HttpServletResponse response,
            HttpServletRequest request) throws FileNotFoundException, IOException{
        
        String logoNew="";
        if(logo!=null&&!logo.isEmpty()){
            String filepath = request.getServletContext().getRealPath("/")+"resources/logo"; 
            Date d = new Date();
            logoNew = d.getTime()+ logo.getOriginalFilename();
            FileOutputStream  fos = new FileOutputStream(filepath+"/"+logoNew);
            fos.write(logo.getBytes());
            fos.close();
        }
        Company comp = companyDao.createCompany(name, address, cityId, companyTypeId, web, email, phone, fax, mat_br, pib, password, about, logoNew);
        HttpSession session = request.getSession();
        session.setAttribute("compId", comp.getCompanyId());
        //response.sendRedirect("company_profile");               PROVERI DA LI FUNKCIONISE
        return "redirect:company_profile";
    }
    
    
    //prihvata redirekciju predhodnog metoda
    @RequestMapping(value="/company_profile")
    public String companyProfile(HttpServletRequest request, ModelMap model){
        HttpSession session = request.getSession();
        if(session.getAttribute("compId")==null){
            model.addAttribute("msg", 3);
            return "redirect:../";
        }else {
            Company comp = companyDao.findById(Short.toUnsignedInt((Short)session.getAttribute("compId")));
            model.addAttribute("comp", comp);
            return "company/company_profile";
        }
    }
    
    
    //zahtev sa index.jsp redirektuje na companyProfile()
    @RequestMapping(value="/company_profile_log")
    public String companyProfileLog( 
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String password,
            //HttpServletResponse response,
            HttpServletRequest request ) throws IOException{
        HttpSession session = request.getSession();
        if(email.isEmpty() || password.isEmpty()){
           //response.sendRedirect("../");               PROVERI DA LI FUNKCIONISE
            return "redirect:../";
        }else {
            Company comp = companyDao.Login(email, password);
            if(comp!=null){
                session.setAttribute("compId", comp.getCompanyId());
                //response.sendRedirect("company_profile"); 
                return "redirect:company_profile";
            }else {
                //response.sendRedirect("../"); 
                return "redirect:../";
            }
        }
    }
    
    
    //zahtev sa company_profile.jsp otvara add_jobs
    @RequestMapping(value="/add_job")
    public String addJob(@RequestParam(required = false) Integer msg, HttpServletRequest request, ModelMap model){
        HttpSession session = request.getSession();
        if(session.getAttribute("compId")==null){
            model.addAttribute("msg", 3);
            return "redirect:../";
        }else {
            List<City> city = cityDao.findAll();
            List<EmploymentType> employmentType = employmentTypeDao.findAll();
            List<JobPosition> jobPosition = jobPositionDao.findAll();
            model.addAttribute("city", city);
            model.addAttribute("employmentType", employmentType);
            model.addAttribute("jobPosition", jobPosition);
            model.addAttribute("duration", JobDuration.list);
            if(session.getAttribute("compId")!=null){
                Company comp = companyDao.findById(Short.toUnsignedInt((Short)session.getAttribute("compId")));
                model.addAttribute("comp", comp);
            }
            if(msg!=null && msg==4){
                model.addAttribute("message", "morate popuniti predvidjena polja"); 
            }
            return "company/add_job";
        }
    }
    
    
    //zahtev sa add_job.jsp redirektuje na companyProfile()
    @RequestMapping(value="/add_job_post")
    public String addJobPost(
            @RequestParam(required = false) String jobTitle,
            @RequestParam(required = false) Integer cityId,
            @RequestParam(required = false) Integer jobPositionId,
            @RequestParam(required = false) Integer employmentTypeId,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String contactPerson,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String email,
            @RequestParam(required = false, defaultValue = "15") Short duration,
            HttpServletRequest request, 
            ModelMap model) throws IOException{
        HttpSession session = request.getSession();
        if(session.getAttribute("compId")==null){
            model.addAttribute("msg", 3);
            return "redirect:../";
        }else if (jobTitle.isEmpty() || description.isEmpty() || cityId==null || employmentTypeId==null || jobPositionId==null){
            model.addAttribute("msg", 4);
            return "redirect:add_job";
        }else{    
            jobDao.createJob(jobTitle, cityId, jobPositionId, employmentTypeId, (Short)session.getAttribute("compId"), address, description, contactPerson, phone, email, duration);
            return "redirect:company_profile";
        }
    }
    
}
