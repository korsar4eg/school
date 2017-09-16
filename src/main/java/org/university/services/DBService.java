package org.university.services;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by KoRsaR on 16.09.17.
 */
@Singleton
public class DBService {


    private  EntityManagerFactory emf;
    private EntityManager em;
    private static DBService instance = null;

    private DBService()
    {
        emf = Persistence.createEntityManagerFactory("pu");
        em = emf.createEntityManager();
    }

    public static synchronized DBService getInstance()
    {
        if (instance == null){
            instance = new DBService();
        }

        return instance;
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public  EntityManager getEm() {
        return em;
    }

    public  void setEm(EntityManager em) {
        this.em = em;
    }
}
