package org.university.services;

import org.university.entites.Group;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean(name = "groupService")
@ApplicationScoped
public class GroupService {

    @ManagedProperty(value = "#{personsService}")
    public PersonsService personsService;

    public List<Group> createGroups()
    {
        List<Group> groups = new ArrayList<Group>();

        groups.add(new Group(1, "group 1", new Date(2017, 0, 0), personsService.createStudents()));
        groups.add(new Group(2, "group 2", new Date(2017, 0, 0), personsService.createStudents()));
        groups.add(new Group(3, "group 3", new Date(2017, 0, 0), personsService.createStudents()));
        groups.add(new Group(4, "group 4", new Date(2017, 0, 0), personsService.createStudents()));
        groups.add(new Group(5, "group 5", new Date(2017, 0, 0), personsService.createStudents()));

        return groups;
    }

    public PersonsService getPersonsService() {
        return personsService;
    }

    public void setPersonsService(PersonsService personsService) {
        this.personsService = personsService;
    }
}
