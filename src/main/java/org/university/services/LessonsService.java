package org.university.services;

import org.apache.tinkerpop.gremlin.structure.T;
import org.hibernate.Transaction;
import org.primefaces.event.RowEditEvent;
import org.university.entites.Lesson;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "lessonsService")
@ApplicationScoped
public class LessonsService {

    @ManagedProperty(value = "#{sessionService}")
    private SessionService sessionService;

    public List<Lesson> createLessons()
    {
        return  sessionService.getSession().createCriteria(Lesson.class).list();
    }

    public SessionService getSessionService() {
        return sessionService;
    }

    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }
}
