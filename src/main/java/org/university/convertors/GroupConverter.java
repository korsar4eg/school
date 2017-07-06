package org.university.convertors;


import org.university.entites.Group;
import org.university.entites.Lesson;
import org.university.services.University;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.List;

@ManagedBean(name = "groupConverterBean")
@FacesConverter(value = "groupConverter")
public class GroupConverter implements Converter {


    @ManagedProperty(value = "#{university}")
    private University university;

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        List<Group> groups = university.getGroups();

        for (Group group: groups){
            if (group.getId() == Integer.parseInt(value)){
                return group;
            }
        }

        return null;
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Group) value).getId() + "";
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }
}
