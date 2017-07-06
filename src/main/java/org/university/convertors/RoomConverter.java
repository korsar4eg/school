package org.university.convertors;


import org.university.entites.Room;
import org.university.services.University;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.List;


@ManagedBean(name = "roomConverterBean")
@FacesConverter(value = "roomConverter")
public class RoomConverter implements Converter{

    @ManagedProperty(value = "#{university}")
    private University university;

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        List<Room> rooms  = university.getRooms();

        for (Room room: rooms){
            if (room.getId().equals(value)){
                return room;
            }
        }

        return null;
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Room) value).getId() + "";
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }
}
