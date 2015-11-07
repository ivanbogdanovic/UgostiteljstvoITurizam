package com.oglasi.model;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class CityDao {
     
    @Autowired
    SessionFactory sessionFactory;
    
    public List<City> findAll(){
        Session session = sessionFactory.getCurrentSession();
        List<City> result = session.createCriteria(City.class).list();
        return result;
    }
    
    public List<City> findByCountry(Short id){
        Session session = sessionFactory.getCurrentSession();
        List<City> result = session.getNamedQuery("City.findByCountry").setShort("countryId", id).list();
        return result;  
    }
     
}
