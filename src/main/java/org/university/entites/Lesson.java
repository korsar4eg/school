package org.university.entites;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;

@ManagedBean
@RequestScoped
@Entity
@Table(name = "lessons")
@Transactional
public class Lesson implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column
    private int code;

    @Column(name = "title")
    private String title;


    public Lesson()
    {

    }

    public Lesson(int code, String title)
    {
        this.code = code;
        this.title = title;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
