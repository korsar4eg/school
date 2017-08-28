package org.university.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.security.auth.login.Configuration;

@ApplicationScoped
@ManagedBean(name = "sessionService")
public class SessionService {

    private Session session;



    public SessionService(){}



    @PostConstruct
    public void init()
    {
        SessionFactory sessionFactory;

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            StandardServiceRegistryBuilder.destroy( registry );

            throw new ExceptionInInitializerError("Initial SessionFactory failed" + e);
        }


        session = sessionFactory.openSession();
    }

    public Session getSession() {
        return session;
    }
}
