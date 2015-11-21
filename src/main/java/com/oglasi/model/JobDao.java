package com.oglasi.model;

import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class JobDao {
    
    @Autowired
    SessionFactory sessionFactory;
    
    
    //glavna pretraga na index strani
    public static Integer numOfPages;
    public List<Job> jobSearchMain(Short country, Integer city, Short companyType, Integer jobPosition, Integer employmentType, Integer page, Integer perPage){
       Session session = sessionFactory.getCurrentSession();
       Criteria criteria = session.createCriteria(Job.class);
       
       criteria.add(Restrictions.eq("status", Status.ACTIVE));
       criteria.createAlias("cityId", "city"); 
       criteria.createAlias("city.countryId", "country");
       criteria.add(Restrictions.eq("country.countryId", country));
       if(city!=null){
            criteria.add(Restrictions.eq("city.cityId", city));
       }
       if(companyType!=null){
            criteria.createAlias("companyId", "company");
            criteria.createAlias("company.companyTypeId", "companyType");
            criteria.add(Restrictions.eq("companyType.companyTypeId", companyType));
       }
       if(jobPosition!=null){
            criteria.createAlias("jobPositionId", "jobPosition");
            criteria.add(Restrictions.eq("jobPosition.jobPositionId", jobPosition));
       }
       if(employmentType!=null){
            criteria.createAlias("employmentTypeId", "employmentType");
            criteria.add(Restrictions.eq("employmentType.employmentTypeId", employmentType));
       } 
      
       Double d = Math.ceil(((double)criteria.list().size()/(double)perPage)) ;
       numOfPages = d.intValue();     //ukupan broj strana
       criteria.setFirstResult(page*perPage-perPage);
       criteria.setMaxResults(perPage);
       List<Job> result = criteria.list();
       return result;
   }
    
    
    public Job findById(Integer id){
       Session session = sessionFactory.getCurrentSession();
       Job result = (Job)session.getNamedQuery("Job.findByJobId").setInteger("jobId", id).uniqueResult();
       return result;
   }
    
    
     public List<Job> findByCompAndStatus(Short id, Short status){
       Session session = sessionFactory.getCurrentSession();
       List<Job> result = session.getNamedQuery("Job.findByCompanyAndStatus").setShort("companyId", id).setShort("status", status).list();
       return result;
   }
     
     
     public Job createJob(String title, Integer cityId, Integer jobPositionId, Integer employmentTypeId, Short companyId, String address, String description, String contactPerson, String phone, String email, Short jobDuration){
       Session session = sessionFactory.getCurrentSession();
       Date createDate = new Date();
       City c = new City();
       c.setCityId(cityId);
       EmploymentType eT = new EmploymentType(employmentTypeId);
       JobPosition jP = new JobPosition(jobPositionId);
       Company com = new Company(companyId);
       Job job = new Job(title, c, jP, eT, com, address, description, contactPerson, phone, email, Status.INACTIVE, createDate, jobDuration);
       session.persist(job);
       return job;
   }
     
     
     public Date expirationDate(Integer id){
        Date result = new Date(findById(id).getActiveDate().getTime()+((long)(1000*60*60*24)*(long)findById(id).getDuration()));
        return result;
     }
     
     
     public List<Job> findByStatuses(Short status, Short status2){
       Session session = sessionFactory.getCurrentSession();
       List<Job> result = session.createQuery("SELECT j FROM Job j WHERE j.status LIKE "+status+" OR j.status LIKE "+status2).list();
       return result;
   }
     
     
     public List<Job> findByStatus(Short status){
       Session session = sessionFactory.getCurrentSession();
       List<Job> result = session.getNamedQuery("Job.findByStatus").setShort("status", status).list();
       return result;
   }
     
     
     public void changeStatus(Integer id, Short status){
       Session session = sessionFactory.getCurrentSession();
       session.createQuery("UPDATE Job SET status = "+status+", activeDate = CURRENT_TIMESTAMP WHERE jobId ="+ id).executeUpdate();
   }
     
     
     public void delete(Integer id){
       Session session = sessionFactory.getCurrentSession();
       session.createQuery("DELETE FROM Job WHERE jobId="+id).executeUpdate();
   }
     
     
     public int jobUpdate(String title, Integer cityId, Integer jobPositionId, Integer employmentTypeId, Short duration, String address, String description, String contactPerson, String phone, String email, Integer id){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("UPDATE Job j SET j.title = :title, j.cityId.cityId = :cityId, j.jobPositionId.jobPositionId = :jobPositionId, j.employmentTypeId.employmentTypeId = :employmentTypeId, j.duration = :duration, j.address = :address, j.description = :description, j.contactPerson = :contactPerson, j.phone = :phone, j.email = :email WHERE j.jobId = :jobId");
        query.setParameter("title", title);
        query.setParameter("cityId", cityId);
        query.setParameter("jobPositionId", jobPositionId);
        query.setParameter("employmentTypeId", employmentTypeId);
        query.setParameter("duration", duration);
        query.setParameter("address", address);
        query.setParameter("description", description);
        query.setParameter("contactPerson", contactPerson);
        query.setParameter("phone", phone);
        query.setParameter("email", email);
        query.setParameter("jobId", id);
        int result = query.executeUpdate();
        return result;
    }
}
