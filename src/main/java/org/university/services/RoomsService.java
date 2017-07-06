package org.university.services;

import org.university.entites.Room;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "roomsService")
@ApplicationScoped
public class RoomsService {

    public List<Room> createRooms()
    {
        List<Room> rooms = new ArrayList<Room>();
        rooms.add(new Room("a100", 30));
        rooms.add(new Room("a101", 30));
        rooms.add(new Room("a102", 30));
        rooms.add(new Room("a103", 30));
        rooms.add(new Room("a104", 30));
        rooms.add(new Room("a105", 30));
        rooms.add(new Room("a106", 30));
        rooms.add(new Room("a107", 30));
        rooms.add(new Room("a108", 30));
        rooms.add(new Room("a109", 30));
        rooms.add(new Room("a110", 30));
        rooms.add(new Room("a111", 30));

        rooms.add(new Room("b100", 30));
        rooms.add(new Room("b101", 30));
        rooms.add(new Room("b102", 30));
        rooms.add(new Room("b103", 30));
        rooms.add(new Room("b104", 30));
        rooms.add(new Room("b105", 30));
        rooms.add(new Room("b106", 30));
        rooms.add(new Room("b107", 30));
        rooms.add(new Room("b108", 30));
        rooms.add(new Room("b109", 30));
        rooms.add(new Room("b110", 30));
        rooms.add(new Room("b111", 30));

        return rooms;
    }

}
