package org.university.services;

import org.university.entites.*;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SessionScoped
@ManagedBean(name = "scheduleService")
public class ScheduleService {

    @ManagedProperty(value = "#{university}")
    private University university;

    private Schedule schedule;

    private List<ScheduleRecord> resultRecords;


    @PostConstruct
    public void init()
    {
        schedule = createSchedule();
        university.setSchedule(schedule);
    }

    public Schedule createSchedule()
    {
        List<ScheduleRecord> list = new ArrayList<ScheduleRecord>();
        List<Room> rooms = university.getRooms();
        List<Group> groups = university.getGroups();
        List<Person> teachers = university.getTeachers();

        long startDate = 1499381985L;
        int i = 0;
        int j = 0;
        int k = 0;

        for (int index = 0; index < 60; index++, i++, j++, k++){

            if (i > rooms.size() - 1){
                i = 0;
            }

            if (j > groups.size() - 1){
                j = 0;
            }

            if (k > teachers.size() - 1){
                k = 0;
            }
            startDate += index * 24 * 3600;
            list.add(new ScheduleRecord(rooms.get(i), groups.get(j), (Teacher) teachers.get(k), new Date(startDate * 1000)));

        }

        return new Schedule(list);

    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public void buildTeacherSchedule(Date startDate, Date finishDate, Boolean isMonth, Teacher teacher)
    {
        if (isMonth){
            resultRecords = schedule.buildTeacherMonthSchedule(teacher, startDate);
        }
        else {
            resultRecords = schedule.buildByDate(teacher, startDate, finishDate);
        }

        System.out.println("count " + resultRecords.size());
    }

    public void buildStudentSchedule(Date startDate, Date finishDate, Boolean isMonth, Student student)
    {
        if (isMonth){
            resultRecords = schedule.buildStudentMonthSchedule(student, startDate);
        }
        else {
            resultRecords = schedule.buildByDate(student, startDate, finishDate);
        }

        System.out.println("count " + resultRecords.size());
    }

    public List<ScheduleRecord> getResultRecords() {
        return resultRecords;
    }

    public void setResultRecords(List<ScheduleRecord> resultRecords) {
        this.resultRecords = resultRecords;
    }

    public void addScheduleRecord(Room room, Teacher teacher, Group group, Date lessonDate)
    {
        List<ScheduleRecord> records =  schedule.getScheduleRecords();
        records.add(new ScheduleRecord(room , group, teacher, lessonDate));
        schedule.setScheduleRecords(records);
        university.setSchedule(schedule);
    }
}
