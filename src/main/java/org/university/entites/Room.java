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
@Table(name = "rooms")
@Transactional
@NamedQueries({
        @NamedQuery(name = "getAllRooms", query = "select r from Room r")
})
public class Room implements Serializable{


    private String id;

    private int capacity;
    private List<ScheduleRecord> scheduleRecord;

    public Room()
    {

    }

    public Room(String id, int capacity, List<ScheduleRecord> scheduleRecord)
    {
        this.id = id;
        this.capacity = capacity;
        this.scheduleRecord = scheduleRecord;
    }

    @Id
    @Column
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "capacity")
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room")
    public List<ScheduleRecord> getScheduleRecord() {
        return scheduleRecord;
    }

    public void setScheduleRecord(List<ScheduleRecord> scheduleRecord) {
        this.scheduleRecord = scheduleRecord;
    }
}
