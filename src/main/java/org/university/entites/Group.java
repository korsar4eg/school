package org.university.entites;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
//
@ManagedBean(name = "group")
@RequestScoped

@Entity
@Table(name = "groups")
@Transactional
public class Group implements Serializable {


    private int id;

    private String title;

    private Date year;

    private List<Student> students;

    private List<ScheduleRecord> scheduleRecord;

    public Group()
    {

    }


    public Group(int id, String title, Date year, List<Student> students, List<ScheduleRecord> scheduleRecord)
    {
        this.id = id;
        this.title = title;
        this.year = year;
        this.students = students;
        this.scheduleRecord = scheduleRecord;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "year")
    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
    public List<ScheduleRecord> getScheduleRecord() {
        return scheduleRecord;
    }

    public void setScheduleRecord(List<ScheduleRecord> scheduleRecord) {
        this.scheduleRecord = scheduleRecord;
    }
}
