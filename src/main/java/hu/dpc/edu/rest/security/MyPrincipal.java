package hu.dpc.edu.rest.security;

import java.security.Principal;

/**
 * Created by vrg on 2016. 11. 11..
 */
public class MyPrincipal implements Principal {

    private String name;

    public MyPrincipal(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyPrincipal that = (MyPrincipal) o;

        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "MyPrincipal{" +
                "name='" + name + '\'' +
                '}';
    }
}
