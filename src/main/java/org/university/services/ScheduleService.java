package org.university.services;

import org.hibernate.Transaction;
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

    @ManagedProperty(value = "#{sessionService}")
    private SessionService sessionService;

    private List<ScheduleRecord> resultRecords;


    @PostConstruct
    public void init()
    {
        schedule = createSchedule();
        university.setSchedule(schedule);
    }

    public Schedule createSchedule()
    {
        Schedule schedule =  new Schedule(sessionService.getSession().createCriteria(ScheduleRecord.class).list());
        schedule.setSessionService(sessionService);
        return schedule;
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
        ScheduleRecord scheduleRecord = new ScheduleRecord(room, group, teacher, lessonDate);
        Transaction tx = sessionService.getSession().beginTransaction();
        sessionService.getSession().save(scheduleRecord);
        tx.commit();
    }

    public SessionService getSessionService() {
        return sessionService;
    }

    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }
}
