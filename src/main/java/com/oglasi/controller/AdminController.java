package com.oglasi.controller;

import com.oglasi.model.City;
import com.oglasi.model.CityDao;
import com.oglasi.model.Company;
import com.oglasi.model.CompanyDao;
import com.oglasi.model.CompanyType;
import com.oglasi.model.CompanyTypeDao;
import com.oglasi.model.EmploymentType;
import com.oglasi.model.EmploymentTypeDao;
import com.oglasi.model.Job;
import com.oglasi.model.JobDao;
import com.oglasi.model.JobDuration;
import com.oglasi.model.JobPosition;
import com.oglasi.model.JobPositionDao;
import com.oglasi.model.Status;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
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
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("admin")
public class AdminController {
    
    @Autowired
    JobDao jobDao;
    
    @Autowired
    CompanyDao companyDao;
    
    @Autowired
    CityDao cityDao;
    
    @Autowired
    CompanyTypeDao companyTypeDao;
    
    @Autowired
    JobPositionDao jobPositionDao;
    
    @Autowired
    EmploymentTypeDao employmentTypeDao;

    @RequestMapping("/")
    public String admin(ModelMap model, @RequestParam(required = false) Integer msg){
        if(msg!=null && msg==1){
           model.addAttribute("message", "uneti podaci su netacni"); 
        }else if(msg!=null && msg==2){
           model.addAttribute("message", "morate biti ulogovani za ovu akciju"); 
        }
        return "admin/admin";
    }
    
    @RequestMapping("/admin_login")
    public String adminLogn(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String password,
            HttpServletRequest request, 
            HttpServletResponse response,
            ModelMap model) throws IOException{
        String adminUsername = "admin";
        String adminPassword = "admin";
        HttpSession session = request.getSession();
        if(username.equals(adminUsername) && password.equals(adminPassword)){
           session.setAttribute("adminPermit", 1);
           return "redirect:admin_main";
        }else {
           model.addAttribute("msg", 1);
           return "redirect:"; 
           //PORUKA: uneti podaci su netacni
        }
    }
    
    @RequestMapping("/admin_sign_out")
    public String signOut(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("adminPermit");
        return "redirect:/";
    }
    
    @RequestMapping("/admin_main")
    public String adminMain(HttpServletRequest request, ModelMap model, @RequestParam(required = false, defaultValue = "2") Short status){
        HttpSession session = request.getSession();
        if(session.getAttribute("adminPermit")==null){
            model.addAttribute("msg", 2);
            return "redirect:../";
        }else {
            List<Job> job = jobDao.findByStatuses(Status.INACTIVE, Status.UPDATE);
            List<Job> sJob= jobDao.findByStatus(status);
            List<Company> company = companyDao.findByStatuses(Status.INACTIVE, Status.UPDATE);
            List<Company> sCompany = companyDao.findByStatus(status);
            model.addAttribute("job", job);
            model.addAttribute("sJob", sJob); 
            model.addAttribute("company", company);
            model.addAttribute("aCompany", sCompany);
            model.addAttribute("status", Status.fields());
            return "admin/admin_main";
        }
    }
    
    
    @RequestMapping("/admin_job/{id}")
    public String adminJob(HttpServletRequest request, ModelMap model, @PathVariable Integer id){
        HttpSession session = request.getSession();
        if(session.getAttribute("adminPermit")==null){
            model.addAttribute("msg", 2);
            return "redirect:../";
        }else {
            Job job = jobDao.findById(id);
            model.addAttribute("job", job);
            return "admin/admin_job";
        }
    }
    
    
    @RequestMapping("/admin_company/{id}")
    public String adminCompany(HttpServletRequest request, ModelMap model, @PathVariable Short id){
        HttpSession session = request.getSession();
        if(session.getAttribute("adminPermit")==null){
            model.addAttribute("msg", 2);
            return "redirect:../";
        }else {
            Company company = companyDao.findById(id);
            List<Job> jobActive = jobDao.findByCompAndStatus(id, Status.ACTIVE);
            List<Job> jobInactive = jobDao.findByCompAndStatus(id, Status.INACTIVE);
            List<Job> jobUpdate = jobDao.findByCompAndStatus(id, Status.UPDATE);
            List<Job> jobExpire = jobDao.findByCompAndStatus(id, Status.EXPIRE);
            model.addAttribute("company", company);
            model.addAttribute("jobA", jobActive);
            model.addAttribute("jobI", jobInactive);
            model.addAttribute("jobU", jobUpdate);
            model.addAttribute("jobE", jobExpire);
            return "admin/admin_company";
        }
    }
    
    
    @RequestMapping("/admin_company_activate/{id}")
    public String activateCompany(HttpServletRequest request, ModelMap model, @PathVariable Short id){
        HttpSession session = request.getSession();
        if(session.getAttribute("adminPermit")==null){
            model.addAttribute("msg", 2);
            return "redirect:../";
        }else {
            companyDao.changeStatus(id, Status.ACTIVE);
            return "redirect:/admin/admin_company/"+id;
        }
    }
    
    
    @RequestMapping("/admin_company_canceled/{id}")
    public String canceledCompany(HttpServletRequest request, ModelMap model, @PathVariable Short id){
        HttpSession session = request.getSession();
        if(session.getAttribute("adminPermit")==null){
            model.addAttribute("msg", 2);
            return "redirect:../";
        }else {
            companyDao.changeStatus(id, Status.CANCELED);
            return "redirect:/admin/admin_company/"+id;
        }
    }
    
    
    @RequestMapping("/admin_company_delete/{id}")
    public String deleteCompany(HttpServletRequest request, ModelMap model, @PathVariable Short id){
        HttpSession session = request.getSession();
        if(session.getAttribute("adminPermit")==null){
            model.addAttribute("msg", 2);
            return "redirect:../";
        }else {
            companyDao.delete(id);
            return "redirect:../admin_main";
        }
    }
    
    
    
    @RequestMapping("/admin_company_update/{id}")
    public String adminCompanyUpdate(HttpServletRequest request, ModelMap model, @PathVariable Short id){
        HttpSession session = request.getSession();
        if(session.getAttribute("adminPermit")==null){
            model.addAttribute("msg", 2);
            return "redirect:../";
        }else {
            Company company = companyDao.findById(id);
            List<City> city = cityDao.findAll();
            List<CompanyType> companyType = companyTypeDao.findAll();
            model.addAttribute("company", company);
            model.addAttribute("city", city);
            model.addAttribute("companyType", companyType);
            return "admin/admin_company_update";
        }
    }
    
    
    @RequestMapping("/admin_company_post")
    public String adminCompanyPost(
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
            @RequestParam(required = false) String about,
            @RequestParam(required = false) Short id,
            HttpServletRequest request, 
            ModelMap model){
        HttpSession session = request.getSession();
        if(session.getAttribute("adminPermit")==null){
            model.addAttribute("msg", 2);
            return "redirect:../";
        }else {
            int result = companyDao.companyUpdate(name, address, email, phone, fax, web, pib, mat_br, about, cityId, companyTypeId, id);
            return "redirect:admin_company/"+id;
        }
    }
    
    
    @RequestMapping("/admin_company_logo")
    public String adminCompanyLogo(
            @RequestParam(required = false) Short id,
            @RequestParam(required = false) MultipartFile logo,
            HttpServletRequest request, 
            ModelMap model) throws FileNotFoundException, IOException{
        HttpSession session = request.getSession();
        if(session.getAttribute("adminPermit")==null){
            model.addAttribute("msg", 2);
            return "redirect:../";
        }else {
            String logoNew="";
            if(logo!=null&&!logo.isEmpty()){
                String filepath = request.getServletContext().getRealPath("/")+"resources/logo"; 
                Date d = new Date();
                logoNew = d.getTime()+ logo.getOriginalFilename();
                FileOutputStream  fos = new FileOutputStream(filepath+"/"+logoNew);
                fos.write(logo.getBytes());
                fos.close();
            }
            companyDao.companyLogoUpdate(id, logoNew);
            return "redirect:admin_company_update/"+id;
        }
    }
    
    
    @RequestMapping("/admin_company_logo_del")
    public String adminCompanyLogoDel(
            @RequestParam(required = false) Short id,
            HttpServletRequest request, 
            ModelMap model){
        HttpSession session = request.getSession();
        if(session.getAttribute("adminPermit")==null){
            model.addAttribute("msg", 2);
            return "redirect:../";
        }else {
            String logo = companyDao.findById(id).getLogo();
            String filepath = request.getServletContext().getRealPath("/")+"resources/logo/"+logo;
            File file = new File(filepath);
            file.delete();
            int result = companyDao.companyLogoDelete(id);
            return "redirect:admin_company_update/"+id;
        }
    }
    
    
    @RequestMapping("/admin_job_activate/{id}")
    public String activateJob(HttpServletRequest request, ModelMap model, @PathVariable Integer id){
        HttpSession session = request.getSession();
        if(session.getAttribute("adminPermit")==null){
            model.addAttribute("msg", 2);
            return "redirect:../";
        }else {
            jobDao.changeStatus(id, Status.ACTIVE);
            return "redirect:/admin/admin_main";
        }
    }
    
    
    @RequestMapping("/admin_job_canceled/{id}")
    public String canceledJob(HttpServletRequest request, ModelMap model, @PathVariable Integer id){
        HttpSession session = request.getSession();
        if(session.getAttribute("adminPermit")==null){
            model.addAttribute("msg", 2);
            return "redirect:../";
        }else {
            jobDao.changeStatus(id, Status.CANCELED);
            return "redirect:/admin/admin_job/"+id;
        }
    }
    
    
    @RequestMapping("/admin_job_delete/{id}")
    public String deleteJob(HttpServletRequest request, ModelMap model, @PathVariable Integer id){
        HttpSession session = request.getSession();
        if(session.getAttribute("adminPermit")==null){
            model.addAttribute("msg", 2);
            return "redirect:../";
        }else {
            jobDao.delete(id);
            return "redirect:../admin_main";
        }
    }
    
    
    @RequestMapping("/admin_job_update/{id}")
    public String adminJobUpdate(HttpServletRequest request, ModelMap model, @PathVariable Integer id){
        HttpSession session = request.getSession();
        if(session.getAttribute("adminPermit")==null){
            model.addAttribute("msg", 2);
            return "redirect:../";
        }else {
            Job job = jobDao.findById(id);
            List<City> city = cityDao.findAll();
            List<JobPosition> jobPosition = jobPositionDao.findAll();
            List<EmploymentType> employmentType = employmentTypeDao.findAll();
            model.addAttribute("job", job);
            model.addAttribute("city", city);
            model.addAttribute("jobPosition", jobPosition);
            model.addAttribute("employmentType", employmentType);
            model.addAttribute("duration", JobDuration.list);
            return "admin/admin_job_update";
        }
    }
    
    
    @RequestMapping("/admin_job_post")
    public String adminJobPost(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) Integer cityId,
            @RequestParam(required = false) Integer jobPositionId,
            @RequestParam(required = false) Integer employmentTypeId,
            @RequestParam(required = false) Short duration,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String contactPerson,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Integer id,
            HttpServletRequest request, 
            ModelMap model){
        HttpSession session = request.getSession();
        if(session.getAttribute("adminPermit")==null){
            model.addAttribute("msg", 2);
            return "redirect:../";
        }else {
            int result = jobDao.jobUpdate(title, cityId, jobPositionId, employmentTypeId, duration, address, description, contactPerson, phone, email, id);
            return "redirect:admin_job/"+id;
        }
    }
    
}
