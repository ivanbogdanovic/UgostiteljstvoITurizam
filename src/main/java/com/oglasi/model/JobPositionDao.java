package com.oglasi.model;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class JobPositionDao {
    
    @Autowired
    SessionFactory sessionFactory;
    
    public List<JobPosition> findAll(){
        Session session = sessionFactory.getCurrentSession();
        List<JobPosition> jobPosition = session.createCriteria(JobPosition.class).list();
        return jobPosition;
    }

}
