package org.university.services;

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
//        List<Lesson> lessons = ;
//        lessons.add(new Lesson(1, "mathematics"));
//        lessons.add(new Lesson(2, "physics"));
//        lessons.add(new Lesson(3, "english"));
//        lessons.add(new Lesson(4, "history"));
        return  sessionService.getSession().createCriteria(Lesson.class).list();
    }


    public SessionService getSessionService() {
        return sessionService;
    }

    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }
}
