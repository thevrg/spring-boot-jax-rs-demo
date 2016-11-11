package hu.dpc.edu;

import org.junit.Test;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

/**
 * Created by vrg on 2016. 11. 11..
 */
public class CustomerProducerTest {

    class MyCustomerProducer implements CustomerProducer {

        @Override
        public Customer produce(String name) {
            final Matcher matcher = Pattern.compile("(\\w+) (\\w+)").matcher(name);
            if (matcher.matches()) {
                return new Customer(matcher.group(1), matcher.group(2));
            } else {
                return new Customer(name, "");
            }
        }
    }

    @Test
    public void testFunctionalInterface() {

        final MyClass myClass = new MyClass();
//        final String firstName = myClass.getFirstNameOfKovacsBela(new CustomerProducer() {
//            @Override
//            public Customer produce(String name) {
//                final Matcher matcher = Pattern.compile("(\\w+) (\\w+)").matcher(name);
//                if (matcher.matches()) {
//                    return new Customer(matcher.group(1), matcher.group(2));
//                } else {
//                    return new Customer(name, "");
//                }
//            }
//        });
        final String firstName = myClass.getFirstNameOfKovacsBela(name -> {
            final Matcher matcher = Pattern.compile("(\\w+) (\\w+)").matcher(name);
            if (matcher.matches()) {
                return new Customer(matcher.group(1), matcher.group(2));
            } else {
                return new Customer(name, "");
            }
        });

        final String first = myClass.getFirstNameOfKovacsBela(name -> new Customer(name));
        final String first2 = myClass.getFirstNameOfKovacsBela(Customer::new);
        final String first3 = myClass.getFirstNameOfKovacsBela(Customer::createCustomerByFullName);

        assertEquals("Bela", firstName);
        assertEquals("Bela Kovacs", first);
        assertEquals("Bela", first3);

        final Logger logger = Logger.getLogger("xxxx");

        if (logger.isLoggable(Level.FINER)) {

            //Heavy algorithm to create log message
            String logMessage = "xxx";

            logger.log(Level.FINER, logMessage);
        }

    }
}
