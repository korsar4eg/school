package org.university.entites;

import java.util.Date;

/**
 * Created by KoRsaR on 02.07.17.
 */
public interface Person {
    int getId();

    void setId(int id);

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getMiddleName();

    void setMiddleName(String middleName);

    Date getDateOfBirth();

    void setDateOfBirth(Date dateOfBirth);

    String getAddress();

    void setAddress(String address);
}
