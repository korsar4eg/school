package org.university.entites;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@ManagedBean
@RequestScoped

@Entity
@Table(name = "teachers")
@Transactional
public class Teacher implements Serializable, Person {


    private int id;

    private String firstName;

    private String lastName;

    private String middleName;

    private Date dateOfBirth;

    private String address;

    private Lesson lesson;

    private String position;
//
//    private int code;

    private List<ScheduleRecord> scheduleRecord;


    public Teacher()
    {

    }

    public Teacher(int id, String firstName, String lastName, String middleName, Date dateOfBirth, String address, Lesson lesson, String position)
    {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.lesson = lesson;
        this.position = position;
//        this.code = code;
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

    @Column(name = "firstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "lastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "middleName")
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Column(name = "dateOfBirth")
  //  @Temporal(javax.persistence.TemporalType.DATE)
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade =
            {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "code")
    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    @Column(name = "position")
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher")
    public List<ScheduleRecord> getScheduleRecord() {
        return scheduleRecord;
    }

    public void setScheduleRecord(List<ScheduleRecord> scheduleRecord) {
        this.scheduleRecord = scheduleRecord;
    }

}
