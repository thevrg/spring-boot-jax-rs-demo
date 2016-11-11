package hu.dpc.edu;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by vrg on 2016. 11. 11..
 */
public class StreamTest {


    final Customer BELA_NAGY = new Customer("Bela", "Nagy");
    final Customer BELA_KOVACS = new Customer("Bela", "Kovacs");

    final List<Customer> customers = Arrays.asList(
            BELA_NAGY,
            BELA_KOVACS,
            new Customer("Ferenc", "Nagy"),
            new Customer("Ferenc", "Kovacs"),
            new Customer("Peter", "Varga")
    );


    @Test
    public void testForEach() {

        for (Customer customer : customers) {
            System.out.println(customer.getFirstName());
        }

        customers.forEach(new Consumer<Customer>() {
            @Override
            public void accept(Customer customer) {
                System.out.println(customer);
            }
        });

        customers.forEach(customer -> System.out.println(customer));

        customers.forEach(System.out::println);


    }

    @Test
    public void testFilter() {

        final Stream<Customer> stream = customers.stream();

        final List<Customer> filtered = stream.filter(new Predicate<Customer>() {
            @Override
            public boolean test(Customer customer) {
                return "Bela".equals(customer.getFirstName());
            }
        }).collect(Collectors.toList());

        System.out.println("Collected filtered list: " + filtered);
        assertThat(filtered.size(), equalTo(2));
    }

    @Test
    public void testFilterWithLambda() {

        final List<Customer> filtered = customers
                .stream()
                .filter(customer -> "Bela".equals(customer.getFirstName()))
                .collect(Collectors.toList());

        System.out.println("Collected filtered list: " + filtered);

        assertThat(filtered.size(), equalTo(2));

        assertThat(filtered, hasItems(BELA_NAGY, BELA_KOVACS));
    }
}
