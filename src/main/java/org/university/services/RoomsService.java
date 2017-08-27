package org.university.services;

import org.university.entites.Room;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "roomsService")
@ApplicationScoped
public class RoomsService {

    @ManagedProperty(value = "#{sessionService}")
    private SessionService sessionService;

    public List<Room> createRooms()
    {
        return sessionService.getSession().createCriteria(Room.class).list();
    }

    public SessionService getSessionService() {
        return sessionService;
    }

    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }
}
