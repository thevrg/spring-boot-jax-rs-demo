package hu.dpc.edu;

import org.springframework.hateoas.ResourceSupport;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public Customer(String firstName) {
        this.firstName = firstName;
    }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static Customer createCustomerByFullName(String fullName) {
        final Matcher matcher = Pattern.compile("(\\w+) (\\w+)").matcher(fullName);
        if (matcher.matches()) {
            return new Customer(matcher.group(1), matcher.group(2));
        } else {
            return new Customer(fullName, "");
        }
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

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
