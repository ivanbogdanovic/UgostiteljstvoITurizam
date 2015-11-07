package com.oglasi.model;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class EmploymentTypeDao {

    @Autowired
    SessionFactory sessionFactory;
    
    public List<EmploymentType> findAll(){
        Session session = sessionFactory.getCurrentSession();
        List<EmploymentType> employmentType = session.createCriteria(EmploymentType.class).list();
        return employmentType;
    }
}
