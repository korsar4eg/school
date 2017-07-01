package org.university.services;

import org.university.entites.Lesson;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "lessonsService")
@ApplicationScoped
public class LessonsService {

    public List<Lesson> createLessons()
    {
        List<Lesson> lessons = new ArrayList<Lesson>();
        lessons.add(new Lesson(1, "mathematics"));
        lessons.add(new Lesson(2, "physics"));
        lessons.add(new Lesson(3, "english"));
        lessons.add(new Lesson(4, "history"));
        return  lessons;

    }

}
