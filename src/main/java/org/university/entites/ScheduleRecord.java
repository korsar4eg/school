package org.university.entites;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
@ManagedBean(name = "scheduleRecord")
@RequestScoped
@Entity
@Table(name = "schedule_records")
@Transactional
@NamedQueries({
        @NamedQuery(name = "getAllScheduleRecords", query = "select r from ScheduleRecord r")
})
public class ScheduleRecord implements Serializable {


    private int id;

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
    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne( fetch = FetchType.LAZY, cascade =
            {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "roomId")
    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade =
            {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "groupId")
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade =
            {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "teacherId")
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Column(name = "lessondate")
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
