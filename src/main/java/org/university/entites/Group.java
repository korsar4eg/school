package org.university.entites;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ManagedBean(name = "group")
@RequestScoped
public class Group implements Serializable {

    private int id;
    private String title;
    private Date year;
    private List<Person> students;


    public Group()
    {

    }


    public Group(int id, String title, Date year, List<Person> students)
    {
        this.id = id;
        this.title = title;
        this.year = year;
        this.students = students;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public List<Person> getStudents() {
        return students;
    }

    public void setStudents(List<Person> students) {
        this.students = students;
    }
}
