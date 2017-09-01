package org.university.services;

import org.apache.tinkerpop.gremlin.structure.T;
import org.hibernate.Transaction;
import org.primefaces.event.RowEditEvent;
import org.university.entites.Lesson;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "lessonsService")
@ApplicationScoped
@Stateless
public class LessonsService {

//    @ManagedProperty(value = "#{sessionService}")
//    private SessionService sessionService;

    EntityManagerFactory emf;
    EntityManager em;

    public List<Lesson> createLessons()
    {
        emf = Persistence.createEntityManagerFactory("pu");
        em = emf.createEntityManager();
        return  em.createNamedQuery("getAllLessons").getResultList();
    }

//    public SessionService getSessionService() {
//        return sessionService;
//    }
//
//    public void setSessionService(SessionService sessionService) {
//        this.sessionService = sessionService;
//    }
}
