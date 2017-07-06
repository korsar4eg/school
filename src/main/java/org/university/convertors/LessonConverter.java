package org.university.convertors;


import org.university.entites.Lesson;
import org.university.services.University;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.List;


@ManagedBean(name = "lessonConverterBean")
@FacesConverter(value = "lessonConverter")
public class LessonConverter implements Converter{

    @ManagedProperty(value = "#{university}")
    private University university;

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        List<Lesson> lessons = university.getLessons();

        for (Lesson lesson: lessons){
            if (lesson.getCode() == Integer.parseInt(value)){
                return lesson;
            }
        }

        return null;
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Lesson) value).getCode() + "";
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }
}
