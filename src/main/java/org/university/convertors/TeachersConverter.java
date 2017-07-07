package org.university.convertors;


import org.university.entites.Person;
import org.university.entites.Student;
import org.university.entites.Teacher;
import org.university.services.University;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.List;


@ManagedBean(name = "teachersConverterBean")
@FacesConverter(value = "teachersConverter")
public class TeachersConverter implements Converter{

    @ManagedProperty(value = "#{university}")
    private University university;

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        List<Person> teachers  = university.getTeachers();

        for (Person teacher: teachers){
            if (teacher.getId() == Integer.parseInt(value)){
                return teacher;
            }
        }

        return null;
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Teacher) value).getId() + "";
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }
}
