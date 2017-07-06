package org.university.services;

import org.university.entites.Lesson;
import org.university.entites.Person;
import org.university.entites.Student;
import org.university.entites.Teacher;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean(name = "personsService")
@ApplicationScoped
public class PersonsService {

    public List<Person> createStudents()
    {
        List<Person> students = new ArrayList<Person>();

        students.add(new Student(1, "Вася", "Вася", "Вася", new Date(1990, 1, 1), "Улица сезам 1"));
        students.add(new Student(2, "Вася1", "Вася1", "Вася1", new Date(1990, 2, 1), "Улица сезам 2"));
        students.add(new Student(3, "Вася2", "Вася2", "Вася2", new Date(1990, 3, 1), "Улица сезам 3"));
        students.add(new Student(4, "Вася3", "Вася3", "Вася3", new Date(1990, 4, 1), "Улица сезам 4"));

        return  students;
    }

    public List<Person> createTeachers()
    {
        List<Person> teachers = new ArrayList<Person>();

        teachers.add(new Teacher(
                1,
                "Петя",
                "Петя",
                "Петя",
                new Date(1970, 1, 1),
                "Студ городок 1",
                new Lesson(1, "mathematics"),
                "Зав. кафедры"
        ));
        teachers.add(new Teacher(
                2,
                "Петя1",
                "Петя1",
                "Петя1",
                new Date(1970, 2, 1),
                "Студ городок 2",
                new Lesson(2, "physics"),
                "Зам. Зав. кафедры"
        ));
        teachers.add(new Teacher(
                3,
                "Петя3",
                "Петя3",
                "Петя3",
                new Date(1970, 3, 1),
                "Студ городок 3",
                new Lesson(3, "english"),
                "Старший преподаватель"
        ));
        teachers.add(new Teacher(
                4,
                "Петя4",
                "Петя4",
                "Петя4",
                new Date(1970, 4, 1),
                "Студ городок 4",
                new Lesson(4, "history"),
                "Аспирант"
        ));

        return teachers;
    }


}
