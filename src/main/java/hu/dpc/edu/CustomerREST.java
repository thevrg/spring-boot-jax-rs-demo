package hu.dpc.edu;

import org.springframework.hateoas.ResourceSupport;

/**
 * Created by vrg on 2016. 11. 10..
 */
public class CustomerREST extends ResourceSupport {

    private Customer content;

    public CustomerREST() {
    }

    public CustomerREST(Customer content) {
        this.content = content;
    }

    public Customer getContent() {
        return content;
    }

    public void setContent(Customer content) {
        this.content = content;
    }
}
