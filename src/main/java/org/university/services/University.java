package org.university.services;

import org.university.entites.*;

import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "university")
@ApplicationScoped
public class University implements Serializable {

    private List<Lesson> lessons;
    private List<Person> teachers;
    private List<Person> students;
    private List<Group> groups;

    @ManagedProperty(value = "#{lessonsService}")
    private LessonsService lessonsService;

    @ManagedProperty(value = "#{personsService}")
    private PersonsService personsService;

    @ManagedProperty(value = "#{groupService}")
    private GroupService groupService;


    @PostConstruct
    public void init()
    {
        lessons = lessonsService.createLessons();
        teachers = personsService.createTeachers();
        students = personsService.createStudents();
        groups = groupService.createGroups();

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

    public void deleteStudentFromGroup(int groupId, int studentId)
    {
       for (int i = 0; i < groups.size(); i++){
            if (groups.get(i).getId() == groupId){
                Group group = groups.get(i);
                List<Person> students = group.getStudents();
                for (int j = 0; j < students.size(); j++){
                    if (students.get(j).getId() == studentId){
                        students.remove(j);
                        groups.remove(i);
                        group.setStudents(students);
                        groups.add(group);
                        break;
                    }
                }

                return;
            }
        }
    }

    public String addLesson(Lesson lesson)
    {
        lessons.add(lesson);
        return "success";
    }

    public String addGroup(Group group)
    {
        groups.add(group);
        return "successGroups";
    }

    public String addStudentToGroup(Group group, Student student)
    {
        System.out.println("yes it is " + group + " " + student);
//        for (int i = 0; i < groups.size(); i++){
//
//            if (groups.get(i).getId() == groupId){
//                Group groupTmp = groups.get(i);
//                List<Person> students = groupTmp.getStudents();
//                Person student = null;
//                for (int j = 0; j < students.size(); j++){
//                    if (students.get(j).getId() == studentId){
//                        student = students.get(i);
//                        break;
//                    }
//                }
//                students.add(student);
//                groupTmp.setStudents(students);
//                groups.remove(i);
//                groups.add(groupTmp);
//                break;
//            }

//        }
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

    public List<Person> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Person> teachers) {
        this.teachers = teachers;
    }

    public List<Person> getStudents() {
        return students;
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
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}