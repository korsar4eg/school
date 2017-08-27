package org.university.entites;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.university.services.SessionService;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.util.*;

@ApplicationScoped
@ManagedBean(name = "schedule")
public class Schedule implements Serializable{

    private List<ScheduleRecord> scheduleRecords;

    @ManagedProperty(value = "#{sessionService}")
    private SessionService sessionService;

    public Schedule(){}

    public Schedule(List<ScheduleRecord> records)
    {
        scheduleRecords = records;
    }

    public List<ScheduleRecord> buildByDate(Date startDate)
    {
        Criteria criteria = sessionService.getSession().createCriteria(ScheduleRecord.class);
        criteria.add(Restrictions.ge("lessonDate", startDate));
        criteria.addOrder(Order.asc("lessonDate"));

        return (List<ScheduleRecord>) criteria.list();
    }

    public List<ScheduleRecord> buildByDate(Date startDate, Date finishDate)
    {
        Criteria criteria = sessionService.getSession().createCriteria(ScheduleRecord.class);
        criteria.add(Restrictions.ge("lessonDate", startDate));
        criteria.addOrder(Order.asc("lessonDate"));
        criteria.add(Restrictions.le("lessonDate", finishDate));

        return (List<ScheduleRecord>) criteria.list();
    }

    public List<ScheduleRecord> buildByDate(Person person, Date startDate, Date finishDate)
    {
        Criteria criteria = sessionService.getSession().createCriteria(ScheduleRecord.class);

        criteria.add(Restrictions.ge("lessonDate", startDate));
        criteria.add(Restrictions.le("lessonDate", finishDate));

        if (person instanceof Teacher){
            criteria.add(Restrictions.eq("teacher.id", person.getId()));
        }
        else {

            //okay here is another query to detect user group id, and i know that it could be done with one query
            Criteria studentCriteria = sessionService.getSession().createCriteria(Student.class);
            studentCriteria.add(Restrictions.eq("id", person.getId()));

            Student student = (Student) studentCriteria.uniqueResult();
            System.out.println(student.getGroup());
            criteria.add(Restrictions.eq("group.id", student.getGroup().getId()));
            //criteria.add(Restrictions.eq("group.students.id", person.getId()));
        }

        criteria.addOrder(Order.asc("lessonDate"));

        return (List<ScheduleRecord>) criteria.list();
    }

    public List<ScheduleRecord> buildFullMonth()
    {
        long finishDate = Calendar.getInstance().getTime().getTime() + 3600 * 24 * 30;
        return buildByDate(Calendar.getInstance().getTime(), new Date(finishDate * 1000));
    }

    public List<ScheduleRecord> buildTeacherMonthSchedule(Teacher teacher, Date startDate)
    {
        long finishDate = startDate.getTime() + 3600 * 24 * 30;
        return buildByDate(teacher, startDate, new Date(finishDate * 1000));
    }

    public List<ScheduleRecord> buildStudentMonthSchedule(Student student,  Date startDate)
    {
        long finishDate = startDate.getTime() + 3600 * 24 * 30;
        return buildByDate(student, startDate, new Date(finishDate * 1000));
    }

    public List<ScheduleRecord> buildGroupMonthSchedule(Group group)
    {
        long finishDate = Calendar.getInstance().getTime().getTime() + 3600 * 24 * 30;
        return buildByDate(group.getStudents().get(0), Calendar.getInstance().getTime(), new Date(finishDate * 1000));
    }

    public List<ScheduleRecord> getScheduleRecords() {
        return scheduleRecords;
    }

    public void setScheduleRecords(List<ScheduleRecord> scheduleRecords) {
        this.scheduleRecords = scheduleRecords;
    }

    public SessionService getSessionService() {
        return sessionService;
    }

    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }
}


