package org.university.entites;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

@ManagedBean(name = "scheduleRecord")
@RequestScoped
public class ScheduleRecord implements Serializable {

    private Room room;
    private Group group;
    private Teacher teacher;
    private Date lessonDate;

    public ScheduleRecord()
    {

    }

    public ScheduleRecord(Room room, Group group, Teacher teacher, Date lessonDate)
    {
        this.room = room;
        this.group = group;
        this.teacher = teacher;
        this.lessonDate = lessonDate;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Date getLessonDate() {
        return lessonDate;
    }

    public void setLessonDate(Date lessonDate) {
        this.lessonDate = lessonDate;
    }


    public static final Comparator<ScheduleRecord> COMPARE_BY_DATE = new Comparator<ScheduleRecord>() {

        public int compare(ScheduleRecord o1, ScheduleRecord o2) {
            if (o1.getLessonDate().getTime() > o2.getLessonDate().getTime()){
                return 1;
            }
            else if (o1.getLessonDate().getTime() < o2.getLessonDate().getTime()){
                return -1;
            }
            else {
                return 0;
            }
        }
    };
}
