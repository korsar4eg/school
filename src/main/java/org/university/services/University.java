package org.university.services;

import org.university.entites.Lesson;

import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "university")
@SessionScoped
public class University implements Serializable {

    private List<Lesson> lessons;

    @ManagedProperty(value = "#{lessonsService}")
    private LessonsService lessonsService;


    @PostConstruct
    public void init()
    {
        lessons = lessonsService.createLessons();
    }

    public List<Lesson> getLessons() {

        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public void setLessonsService(LessonsService service) {
        this.lessonsService = service;
    }

    public void addLesson()
    {
        lessons.add(lessons.get(lessons.size() - 1));

    }


}
