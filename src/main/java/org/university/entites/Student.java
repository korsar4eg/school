package org.university.entites;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Date;


@ManagedBean
@RequestScoped
@Entity
@Table(name = "students")
@Transactional
public class Student implements Serializable, Person {


    private int id;

    private String firstName;

    private String lastName;

    private String middleName;

    private Date dateOfBirth;

    private String address;

    private Group group;

    public Student()
    {

    }

    public Student(int id, String firstName, String lastName, String middleName, Date dateOfBirth, String address, Group group)
    {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.group = group;
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
    @JoinColumn(name = "groupId")
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
