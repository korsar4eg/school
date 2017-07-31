package org.university.services;

import org.apache.tinkerpop.gremlin.structure.T;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.university.entites.*;

import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "university")
@ApplicationScoped
public class University implements Serializable {

    private List<Lesson> lessons;
    private List<Person> teachers;
    private List<Person> students;
    private List<Group> groups;
    private List<Room> rooms;
    private Schedule schedule;

    private List<ScheduleRecord> resultRecords;

    @ManagedProperty(value = "#{lessonsService}")
    private LessonsService lessonsService;

    @ManagedProperty(value = "#{personsService}")
    private PersonsService personsService;

    @ManagedProperty(value = "#{groupService}")
    private GroupService groupService;

    @ManagedProperty(value = "#{roomsService}")
    private RoomsService roomsService;

    @ManagedProperty(value = "#{sessionService}")
    private SessionService sessionService;


    @PostConstruct
    public void init()
    {
      //  lessons = lessonsService.createLessons();
        teachers = personsService.createTeachers();
        students = personsService.createStudents();
        groups = groupService.createGroups();
        rooms = roomsService.createRooms();
    }

    public List<Lesson> getLessons() {

        return lessonsService.createLessons();
    }

   // public void setLessons(List<Lesson> lessons) {
//        this.lessons = lessons;
//    }

    public void setLessonsService(LessonsService service) {
        this.lessonsService = service;
    }

    public void setPersonsService(PersonsService service) {
        this.personsService = service;
    }

    public String addTeacher(Person teacher)
    {
        teachers.add(teacher);
        return "successTeacher";
    }

    public String addStudent(Person student)
    {
        students.add(student);
        return "successTeacher";
    }

    private List<Person> deletePerson(int id, List<Person> persons)
    {
        for (Person person: persons){
            if (person.getId() == id){
                persons.remove(person);
                return persons;
            }
        }

        return persons;
    }

    public void deleteTeacher(int id)
    {
        teachers = deletePerson(id, teachers);
    }

    public void deleteStudent(int id)
    {
        students = deletePerson(id, students);
    }

    public void deleteGroup(int id)
    {
        for (Group group: groups){
            if (group.getId() == id){
                groups.remove(group);
                return;
            }
        }
    }

    public void deleteStudentFromGroup(int studentId)
    {
        Session session = sessionService.getSession();
        Student student = (Student) session.get(Student.class, studentId);
        student.setGroup(null);
        Transaction tx = session.beginTransaction();
        session.update(student);
        tx.commit();
    }


    public void addRoom(Room room)
    {
        rooms.add(room);
    }

    public String addGroup(Group group)
    {
        groups.add(group);
        return "successGroups";
    }



    public String addStudentToGroup(Group group, Student student)
    {

        student.setGroup(group);
        Transaction tx = sessionService.getSession().beginTransaction();
        sessionService.getSession().update(student);
        tx.commit();
        return "successGroupsToStudents";
    }

    public void deleteLesson(int code)
    {
       for (Lesson lesson: lessons){
           if (lesson.getCode() == code){
               lessons.remove(lesson);
               return;
           }
       }
    }

    public void deleteRoom(String code)
    {
        for (Room room: rooms){
            if (room.getId().equals(code)){
                rooms.remove(room);
                return;
            }
        }
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

    public GroupService getGroupService() {
        return groupService;
    }

    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }

    public List<Group> getGroups() {
        return groupService.createGroups();
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public RoomsService getRoomsService() {
        return roomsService;
    }

    public void setRoomsService(RoomsService roomsService) {
        this.roomsService = roomsService;
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


    public SessionService getSessionService() {
        return sessionService;
    }

    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    //new updates
    public String add(Object entity) {
        Transaction tx = sessionService.getSession().beginTransaction();
        sessionService.getSession().save(entity);
        tx.commit();
        if (entity instanceof Lesson){
            return "success";
        }
        else if (entity instanceof Group){
            return "successGroups";
        }
        else if (entity instanceof Person){
            return "successTeacher";
        }
        else if (entity instanceof Room){
            return "successRooms";
        }
        else return "success";

    }


    public void edit(RowEditEvent entity) {
            Transaction tx = sessionService.getSession().beginTransaction();
            sessionService.getSession().update(entity.getObject());
            tx.commit();
            RequestContext.getCurrentInstance().execute("PF('dataTable').filter()");
    }

    public void delete(Object entity) {
        Transaction tx = sessionService.getSession().beginTransaction();
        sessionService.getSession().delete(entity);
        tx.commit();
    }


}
