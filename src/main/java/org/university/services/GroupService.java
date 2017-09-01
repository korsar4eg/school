package org.university.services;

import org.university.entites.Group;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean(name = "groupService")
@ApplicationScoped
@Stateless
public class GroupService {


//    @ManagedProperty(value = "#{sessionService}")
//    private SessionService sessionService;
    private EntityManagerFactory emf;
    private EntityManager em;

    public List<Group> createGroups()
    {

        //return sessionService.getSession().createCriteria(Group.class).list();

        emf = Persistence.createEntityManagerFactory("pu");
        em = emf.createEntityManager();
        return em.createNamedQuery("getAllGroups").getResultList();
    }

//    public SessionService getSessionService() {
//        return sessionService;
//    }
//
//    public void setSessionService(SessionService sessionService) {
//        this.sessionService = sessionService;
//    }
}
