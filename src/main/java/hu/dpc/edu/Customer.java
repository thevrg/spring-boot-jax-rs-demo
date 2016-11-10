package hu.dpc.edu;

import java.util.Date;

/**
 * Created by vrg on 2016. 11. 07..
 */
public class Customer implements Cloneable {
    private Long id;

    private String firstName;
    private String lastName;
    private Date birthDate;

    public Customer() {
    }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public Customer clone()  {
        try {
            Customer cloned =  (Customer) super.clone();
            if (birthDate != null) {
                cloned.birthDate = (Date) birthDate.clone();
            }
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return birthDate;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.birthDate = dateOfBirth;
    }

    public boolean isActive() {
        return true;
    }

    public void setActive(boolean active) {

    }
}
