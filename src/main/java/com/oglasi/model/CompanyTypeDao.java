package com.oglasi.model;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class CompanyTypeDao {
    
    @Autowired
    SessionFactory sessionFactory;
    
     public List<CompanyType> findAll(){
       Session session = sessionFactory.getCurrentSession();
       List<CompanyType> result = session.createCriteria(CompanyType.class).list();
       return result;
   }

}
