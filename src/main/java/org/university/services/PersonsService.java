package org.university.services;

import org.university.entites.Lesson;
import org.university.entites.Person;
import org.university.entites.Student;
import org.university.entites.Teacher;

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
import java.util.Date;
import java.util.List;

@ManagedBean(name = "personsService")
@ApplicationScoped
@Stateless
public class PersonsService {

    public List<Person> createStudents()
    {

        return DBService.getInstance()
                        .getEm()
                        .createNamedQuery("getAllStudents")
                        .getResultList();
    }

    public List<Person> createTeachers()
    {
        return DBService.getInstance()
                        .getEm()
                        .createNamedQuery("getAllTeachers")
                        .getResultList();
    }


}
