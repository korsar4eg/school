package org.university.services;

import org.university.entites.Group;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean(name = "groupService")
@ApplicationScoped
@Stateless
public class GroupService {



    public List<Group> createGroups()
    {
        return DBService.getInstance().getEm().createNamedQuery("getAllGroups").getResultList();
    }

}
