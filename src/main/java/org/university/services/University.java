package org.university.services;

import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.university.entites.*;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.*;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "university")
@ApplicationScoped
public class
University implements Serializable {

    private List<Lesson> lessons;
    private List<Person> teachers;
    private List<Person> students;
    private List<Group> groups;
    private List<Room> rooms;
    private Schedule schedule;

    private List<ScheduleRecord> resultRecords;

    @EJB
    private LessonsService lessonsService;

    @EJB
    private PersonsService personsService;

    @EJB
    private GroupService groupService;

    @EJB
    private RoomsService roomsService;


    @PostConstruct
    public void init()
    {
        lessons = lessonsService.createLessons();
        teachers = personsService.createTeachers();
        students = personsService.createStudents();
        groups = groupService.createGroups();
        rooms = roomsService.createRooms();
    }

    public List<Lesson> getLessons() {

        return lessonsService.createLessons();
    }

    public void deleteStudentFromGroup(int studentId)
    {

        Student student = (Student) DBService.getInstance().getEm().find(Student.class, studentId);
        student.setGroup(null);
        DBService.getInstance().getEm().getTransaction().begin();
        DBService.getInstance().getEm().merge(student);
        DBService.getInstance().getEm().getTransaction().commit();
    }




    public String addStudentToGroup(Group group, Student student)
    {

        student.setGroup(group);
        DBService.getInstance().getEm().getTransaction().begin();
        DBService.getInstance().getEm().merge(student);
        DBService.getInstance().getEm().getTransaction().commit();
        return "successGroupsToStudents";

    }


    public List<Person> getTeachers() {
        return personsService.createTeachers();
    }

    public void setTeachers(List<Person> teachers) {
        this.teachers = teachers;
    }

    public List<Person> getStudents() {
        return personsService.createStudents();
    }

    public void setStudents(List<Person> students) {
        this.students = students;
    }


    public List<Group> getGroups() {
        return groupService.createGroups();
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }


    public List<Room> getRooms() {
        return roomsService.createRooms();
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }



    //new updates
    public String add(Object entity) {
        DBService.getInstance().getEm().getTransaction().begin();
        DBService.getInstance().getEm().persist(entity);
        DBService.getInstance().getEm().getTransaction().commit();

        if (entity instanceof Lesson){
            return "success";
        }
        else if (entity instanceof Group){
            return "successGroups";
        }
        else if (entity instanceof Teacher){
            return "successTeacher";
        }
        else if (entity instanceof Student){
            return "successStudent";
        }
        else if (entity instanceof Room){
            return "successRooms";
        }
        else return "success";

    }


    public void edit(RowEditEvent entity) {
        DBService.getInstance().getEm().getTransaction().begin();
        DBService.getInstance().getEm().merge(entity);
        DBService.getInstance().getEm().getTransaction().commit();

        RequestContext.getCurrentInstance().execute("PF('dataTable').filter()");
    }

    public void delete(Object entity) {
        DBService.getInstance().getEm().getTransaction().begin();
        DBService.getInstance().getEm().remove(entity);
        DBService.getInstance().getEm().getTransaction().commit();
    }


}
