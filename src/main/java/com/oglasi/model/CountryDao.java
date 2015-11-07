package com.oglasi.model;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class CountryDao {
    
    @Autowired
    SessionFactory sessionFactory;
    
    
    public List<Country> findAll(){
       Session session = sessionFactory.getCurrentSession();
       List<Country> result = session.createCriteria(Country.class).list();
       return result;
   }

}
