package org.university.convertors;


import org.university.entites.Person;
import org.university.entites.Student;
import org.university.services.University;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.List;


@ManagedBean(name = "studentsConverterBean")
@FacesConverter(value = "studentsConverter")
public class StudentsConverter implements Converter{

    @ManagedProperty(value = "#{university}")
    private University university;

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
//        List<Person> students  = university.getStudents();
//
//        for (Person student: students){
//            if (student.getId() == Integer.parseInt(value)){
//                return student;
//            }
//        }

        return null;
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Student) value).getId() + "";
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }
}
