import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.hibernate.query.Query;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.RowEditEvent;
import org.university.entites.*;
import org.university.services.ScheduleService;
import org.university.services.SessionService;
import org.university.services.University;

import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import javax.faces.component.behavior.Behavior;
import javax.faces.component.behavior.BehaviorBase;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.BehaviorEvent;
import javax.faces.event.FacesEvent;
import javax.faces.event.FacesListener;
import javax.faces.render.Renderer;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.*;

/**
 * Created by KoRsaR on 27.08.17.
 */
public class UniversityTest {

    private SessionService sessionService;
    private ScheduleService scheduleService;
    private University university;
    private List<Student> students;
    private List<Teacher> teachers;
    private List<Room> rooms;
    private List<Group> groups;
    private List<ScheduleRecord> list;


    public UniversityTest()
    {
        sessionService = new SessionService();
        sessionService.init();
        university = new University();

        scheduleService = new ScheduleService();
        scheduleService.setSessionService(sessionService);

        university.setSessionService(sessionService);
        Transaction tx = sessionService.getSession().beginTransaction();

        Query query = sessionService.getSession().createQuery("delete from ScheduleRecord ");
        query.executeUpdate();

         query = sessionService.getSession().createQuery("delete from Group ");
        query.executeUpdate();

        query = sessionService.getSession().createQuery("delete from Room ");
        query.executeUpdate();

        query = sessionService.getSession().createQuery("delete from Teacher ");
        query.executeUpdate();

        query = sessionService.getSession().createQuery("delete from Student ");
        query.executeUpdate();

        query = sessionService.getSession().createQuery("delete from Lesson ");
        query.executeUpdate();



        tx.commit();

        students = new ArrayList<Student>();

        students.add(new Student(1, "Вася", "Вася", "Вася", new Date(1990, 1, 1), "Улица сезам 1", null));
        students.add(new Student(2, "Вася1", "Вася1", "Вася1", new Date(1990, 2, 1), "Улица сезам 2", null));
        students.add(new Student(3, "Вася2", "Вася2", "Вася2", new Date(1990, 3, 1), "Улица сезам 3", null));
        students.add(new Student(4, "Вася3", "Вася3", "Вася3", new Date(1990, 4, 1), "Улица сезам 4", null));


        teachers = new ArrayList<Teacher>();

        teachers.add(new Teacher(1, "Вася", "Вася", "Вася", new Date(1990, 1, 1), "Улица сезам 1", null, ""));
        teachers.add(new Teacher(2, "Вася1", "Вася1", "Вася1", new Date(1990, 2, 1), "Улица сезам 2", null, ""));
        teachers.add(new Teacher(3, "Вася2", "Вася2", "Вася2", new Date(1990, 3, 1), "Улица сезам 3", null, ""));
        teachers.add(new Teacher(4, "Вася3", "Вася3", "Вася3", new Date(1990, 4, 1), "Улица сезам 4", null, ""));


        rooms = new ArrayList<Room>();

        rooms.add(new Room("a100",30, null));

        Group group = new Group();
        group.setId(1);
        group.setTitle("test");

        group.setYear(new Date(2017));
        group.setStudents(students);



        list = new ArrayList<ScheduleRecord>();
        groups = new ArrayList<>();

//        List<Person> teachers = university.getTeachers();

        groups.add(group);

        long startDate = 1499381985L;
        int i = 0;
        int j = 0;
        int k = 0;

        for (int index = 0; index < 60; index++){

//            if (i > rooms.size() - 1){
//                i = 0;
//            }
//
//            if (j > groups.size() - 1){
//                j = 0;
//            }
//
//            if (k > teachers.size() - 1){
//                k = 0;
//            }
            startDate += index * 24 * 3600;
        //    scheduleService.addScheduleRecord(rooms.get(0),teachers.get(0), group, new Date(startDate * 1000));

        }


    }


    @Test
    public void testCrud()
    {
        Lesson lesson = new Lesson();
        lesson.setCode(1);
        lesson.setTeacher(teachers);
        lesson.setTitle("title");
        assertEquals("successGroups", university.add(groups.get(0)));
        assertEquals("successRooms", university.add(rooms.get(0)));
        assertEquals("success", university.add(lesson));
        assertEquals("successStudent", university.add(students.get(0)));
        assertEquals("successTeacher", university.add(teachers.get(0)));


        Date startDate = new Date();
        scheduleService.setUniversity(university);
        scheduleService.init();

        scheduleService.addScheduleRecord(rooms.get(0), teachers.get(0), groups.get(0), startDate);
        Long diff = 24 * 3600 * 1000L;

        Long t1 = startDate.getTime() - diff;
        Long t2 = startDate.getTime() + diff;
        List<ScheduleRecord> result = scheduleService.buildTeacherSchedule(new Date(t1) , new Date(t2), false, teachers.get(0));

        assertEquals(1, result.size());

//        String testString = "edited title";
//
//        lesson.setTitle(testString);
//
//        RowEditEvent event = new RowEditEvent(new DataTable(), new BehaviorBase(), lesson);
//
//        university.edit(event);
//
//        Criteria criteriaAfter = sessionService.getSession().createCriteria(Lesson.class);
//        criteriaAfter.add(Restrictions.eq("code", 1));
//        Lesson lessonAfter = (Lesson) criteriaAfter.uniqueResult();
//
//        assertEquals(testString, lessonAfter.getTitle());



    }




}
