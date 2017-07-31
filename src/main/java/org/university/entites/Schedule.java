package org.university.entites;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.*;

@ApplicationScoped
@ManagedBean(name = "schedule")
public class Schedule implements Serializable{

    private List<ScheduleRecord> scheduleRecords;


    public Schedule(){}

    public Schedule(List<ScheduleRecord> records)
    {
        scheduleRecords = records;
    }

    public List<ScheduleRecord> buildByDate(Date startDate)
    {
        List<ScheduleRecord> result = new ArrayList<ScheduleRecord>();
        for (ScheduleRecord record: scheduleRecords){

            if (record.getLessonDate().getTime() == startDate.getTime()){
                result.add(record);
            }
        }

        Collections.sort(result, ScheduleRecord.COMPARE_BY_DATE);

        return result;
    }

    public List<ScheduleRecord> buildByDate(Date startDate, Date finishDate)
    {
        List<ScheduleRecord> result = new ArrayList<ScheduleRecord>();
        for (ScheduleRecord record: scheduleRecords){

            if (
                    record.getLessonDate().getTime() >= startDate.getTime()
                    && record.getLessonDate().getTime() <= finishDate.getTime()
                )
            {
                result.add(record);
            }
        }

        Collections.sort(result, ScheduleRecord.COMPARE_BY_DATE);

        return result;
    }

    public List<ScheduleRecord> buildByDate(Person person, Date startDate, Date finishDate)
    {

        StringBuilder query = new StringBuilder("from ScheduleRecord ");

        query.append(" where lessonDate >= " + startDate + " and lessonDate <= " + finishDate);

        if (person instanceof Student){
            query.append(" teacher.id =  " + person.getId());
        }
        else {
            //query.append(" groupid.students.id  in  " + person.getId());
        }

//        List<ScheduleRecord> result = new ArrayList<ScheduleRecord>();
//        for (ScheduleRecord record: scheduleRecords){
//
//            if (record.getLessonDate().getTime() >= startDate.getTime() && record.getLessonDate().getTime() <= finishDate.getTime())
//            {
//                if ((person instanceof Teacher) && record.getTeacher().getId() == person.getId())
//                {
//                    result.add(record);
//                }
//
//                if (person instanceof Student){
//                   List<Person> students = record.getGroup().getStudents();
//                   for (Person student: students){
//                       if (student.getId() == person.getId()){
//                           result.add(record);
//                       }
//                   }
//                }
//
//            }
//        }
//
//        Collections.sort(result, ScheduleRecord.COMPARE_BY_DATE);

        return null;
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
}
