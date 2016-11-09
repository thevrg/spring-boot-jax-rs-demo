package hu.dpc.edu;

import java.util.List;
import java.util.function.Predicate;

/**
 * Created by vrg on 2016. 11. 07..
 */
public interface CustomerRepository {
    public void addCustomer(Customer customer);
    public void removeCustomer(Long customerId);
    public void updateCustomer(Customer customer);
    public Customer findById(Long id);
    public List<Customer> findAll();
    public List<Customer>findByPredicate(Predicate<Customer> predicate);
}
