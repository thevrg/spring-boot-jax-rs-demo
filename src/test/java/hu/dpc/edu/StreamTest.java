package hu.dpc.edu;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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

    @Test
    public void testFilterWithLambda2() {

        final List<Customer> filtered = customers
                .stream()
                .filter(customer -> "Bela".equals(customer.getFirstName()))
                .filter(customer -> "Kovacs".equals(customer.getLastName()))
                .collect(Collectors.toList());

        System.out.println("Collected filtered list: " + filtered);

        assertThat(filtered.size(), equalTo(1));

        assertThat(filtered, hasItems(BELA_KOVACS));
    }

    @Test
    public void testMapping() {

        final List<String> filteredAndMapped = customers
                .stream()
                .filter(customer -> "Bela".equals(customer.getFirstName()))
                .map(new Function<Customer, String>() {
                    @Override
                    public String apply(Customer customer) {
                        return customer.getLastName();
                    }
                })
                .filter(lastName -> lastName.startsWith("N"))
                .collect(Collectors.toList());

        System.out.println("Collected filtered list: " + filteredAndMapped);

        assertThat(filteredAndMapped.size(), equalTo(1));

        assertThat(filteredAndMapped, hasItems("Nagy"));
    }

    @Test
    public void testMappingWithLambda() {

        final List<String> filteredAndMapped = customers
                .stream()
                .filter(customer -> "Bela".equals(customer.getFirstName()))
                .map(customer -> customer.getLastName())
                .filter(lastName -> lastName.startsWith("N"))
                .collect(Collectors.toList());

        System.out.println("Collected filtered list: " + filteredAndMapped);

        assertThat(filteredAndMapped.size(), equalTo(1));

        assertThat(filteredAndMapped, hasItems("Nagy"));
    }

    @Test
    public void testIntStreamCollector() {


        List<String> result = customers
                .stream()
                .map(customer -> customer.getLastName() + " " + customer.getFirstName())
                .mapToInt(name -> name.length())
                .filter(length -> length < 13)
                .collect(new Supplier<List<String>>() {
                             @Override
                             public List<String> get() {
                                 return new ArrayList<String>();
                             }
                         },
                        new ObjIntConsumer<List<String>>() {
                            @Override
                            public void accept(List<String> accumulator, int item) {
                                accumulator.add(String.valueOf(item));
                            }
                        }, new BiConsumer<List<String>, List<String>>() {
                            @Override
                            public void accept(List<String> accumulator1, List<String> accumulator2) {
                                accumulator1.addAll(accumulator2);
                            }
                        });

        System.out.println("Collected result: " + result);


    }

    @Test
    public void testIntStreamCollectorWithLambda() {


        List<String> result = customers
                .stream()
                .map(customer -> customer.getLastName() + " " + customer.getFirstName())
                .mapToInt(name -> name.length())
                .filter(length -> length < 13)
                .collect(() -> new ArrayList<String>(),
                        (accumulator,item) -> accumulator.add(String.valueOf(item)),
                        (accumulator1, accumulator2) -> accumulator1.addAll(accumulator2));

        System.out.println("Collected result: " + result);


    }

    @Test
    public void testIntStreamCollectorWithLambda2() {


        int sum = customers
                .stream()
                .map(customer -> customer.getLastName() + " " + customer.getFirstName())
                .mapToInt(name -> name.length())
                .filter(length -> length < 13)
                .sum();

        System.out.println("Sum: " + sum);


    }

    @Test
    public void testIntStreamReduce() {

        int product = IntStream.of(1,2,3,4,5)
                .reduce(1, new IntBinaryOperator() {
                    @Override
                    public int applyAsInt(int left, int right) {
                        return left * right;
                    }
                });

        System.out.println("Product: " + product);


    }
    @Test
    public void testIntStreamReduceWithLambda() {


        int product = customers
                .stream()
                .map(customer -> customer.getLastName() + " " + customer.getFirstName())
                .mapToInt(name -> name.length())
                .reduce(1, (left, right) -> left * right);

        System.out.println("Product: " + product);


    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testStreamStates() {
        final Stream<String> stream = Stream.of("A", "B", "C", "AA", "BB", "CC")
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) {
                        return s.length() > 1;
                    }
                });

        final List<String> list1 = stream.collect(Collectors.toList());

        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("closed");

        stream.filter(s -> s.startsWith("A")).collect(Collectors.toList());

    }
}
