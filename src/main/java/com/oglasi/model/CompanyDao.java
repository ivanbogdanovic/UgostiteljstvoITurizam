package com.oglasi.model;

import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class CompanyDao {
    
    @Autowired
    SessionFactory sessionFactory;
    
    public Company findById(int id){
        Session session = sessionFactory.getCurrentSession();
        Company result = (Company)session.getNamedQuery("Company.findByCompanyId").setInteger("companyId", id).uniqueResult();
        return result;
   }

    public Company createCompany(String name, String address, Integer cityId, Short companyTypeId, String web, String email, String phone, String fax, String mat_br, String pib, String password, String about, String logo){
        
        Session session = sessionFactory.getCurrentSession();
        Date createDate = new Date();
        Date lastUpdate = new Date();
        City c = new City(cityId);
        CompanyType ct = new CompanyType(companyTypeId);
        
        Company company = new Company(name, address, web, email, phone, fax, mat_br, pib, password, about, logo, lastUpdate, createDate, c, ct, Status.INACTIVE);
        session.persist(company);
        return company;
    }
    
    public Company Login(String email, String password){
        Session session = sessionFactory.getCurrentSession();
        Company company = (Company)session.getNamedQuery("Company.findByLogin").setString("email", email).setString("password", password).uniqueResult();
        return company;
    }
    
    public List<Company> findByStatuses(Short status, Short status2){
       Session session = sessionFactory.getCurrentSession();
       List<Company> result = session.createQuery("SELECT c FROM Company c WHERE c.status LIKE "+status+" OR c.status LIKE "+status2).list();
       return result;
   }
    
    
    public List<Company> findByStatus(Short status){
       Session session = sessionFactory.getCurrentSession();
       List<Company> result = session.getNamedQuery("Company.findByStatus").setShort("status", status).list();
       return result;
   }
    
    
    public int companyUpdate(String name, String address, String email, String phone, String fax, String web, String pib, String maticniBroj, String about, Integer cityId, Short companyTypeId, Short companyId){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("UPDATE Company c SET c.name = :name, c.address = :address, c.email = :email, c.phone = :phone, c.fax = :fax, c.web = :web, c.pib = :pib, c.maticniBroj = :maticniBroj, c.about = :about, c.cityId.cityId = :cityId, c.companyTypeId.companyTypeId = :companyTypeId WHERE c.companyId = :companyId");
        query.setParameter("name", name);
        query.setParameter("address", address);
        query.setParameter("email", email);
        query.setParameter("phone", phone);
        query.setParameter("fax", fax);
        query.setParameter("web", web);
        query.setParameter("pib", pib);
        query.setParameter("maticniBroj", maticniBroj);
        query.setParameter("about", about);
        query.setParameter("cityId", cityId);
        query.setParameter("companyTypeId", companyTypeId);
        query.setParameter("companyId", companyId);
        int result = query.executeUpdate();
        return result;
    }
    
    
    public int companyLogoUpdate(Short companyId, String logo){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("UPDATE Company c SET c.logo = :logo WHERE c.companyId = :companyId");
        query.setParameter("logo", logo);
        query.setParameter("companyId", companyId);
        int result = query.executeUpdate();
        return result;
    }
    
    
    public int companyLogoDelete(Short companyId){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("UPDATE Company c SET c.logo = :logo WHERE c.companyId = :companyId");
        query.setParameter("logo", "");
        query.setParameter("companyId", companyId);
        int result = query.executeUpdate();
        return result;
    }
    
    
     public void changeStatus(Short id, Short status){
       Session session = sessionFactory.getCurrentSession();
       session.createQuery("UPDATE Company SET status = "+status+" WHERE companyId ="+ id).executeUpdate();
   }
     
     
     public void delete(Short id){
       Session session = sessionFactory.getCurrentSession();
       session.createQuery("DELETE FROM Company WHERE companyId="+id).executeUpdate();
   }
}
