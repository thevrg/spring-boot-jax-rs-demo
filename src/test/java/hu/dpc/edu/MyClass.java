package hu.dpc.edu;

/**
 * Created by vrg on 2016. 11. 11..
 */
public class MyClass {

    public String getFirstNameOfKovacsBela(CustomerProducer producer) {
        return producer.produce("Bela Kovacs").getFirstName();
    }
}
