package org.university.entites;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@RequestScoped
@Entity
@Table(name = "lessons")
@Transactional
@NamedQueries({
        @NamedQuery(name = "getAllLessons", query = "select l from Lesson l")
})
public class Lesson implements Serializable {


    private int code;


    private String title;

    private List<Teacher> teacher;


    public Lesson()
    {

    }

    public Lesson(int code, String title, List<Teacher> teacher)
    {
        this.code = code;
        this.title = title;
        this.teacher = teacher;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lesson")
    public List<Teacher> getTeacher() {
        return teacher;
    }

    public void setTeacher(List<Teacher> teacher) {
        this.teacher = teacher;
    }
}
