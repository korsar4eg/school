package org.university.services;

import org.university.entites.Room;

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

@Stateless
@ManagedBean(name = "roomService")
@ApplicationScoped
public class RoomsService {



    public List<Room> createRooms()
    {
        return DBService.getInstance()
                        .getEm()
                        .createNamedQuery("getAllRooms")
                        .getResultList();
    }
}
