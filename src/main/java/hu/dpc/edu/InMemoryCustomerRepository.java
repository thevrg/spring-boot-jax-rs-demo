package hu.dpc.edu;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by vrg on 2016. 11. 07..
 */
@Repository
public class InMemoryCustomerRepository implements CustomerRepository, ApplicationContextAware {

    private Map<Long,Customer> customerMap = new HashMap<>();
    private AtomicLong counter = new AtomicLong();

    private ApplicationContext context;

    @Override
    public long addCustomer(Customer customer) {
        final long id = counter.incrementAndGet();
        final Customer managedCustomer = customer.clone();
        managedCustomer.setId(id);
        customerMap.put(id, managedCustomer);
        return id;
    }

    @Override
    public void removeCustomer(Long customerId) {
        customerMap.remove(customerId);
    }

    @Override
    public void updateCustomer(Customer customer) {
        final Customer cloned = customer.clone();
        final Customer managedCustomer = customerMap.get(customer.getId());
        managedCustomer.setFirstName(cloned.getFirstName());
        managedCustomer.setLastName(cloned.getLastName());
        managedCustomer.setActive(cloned.isActive());
        managedCustomer.setDateOfBirth(cloned.getDateOfBirth());
    }

    @Override
    public Customer findById(Long id) {
        return customerMap.get(id);
    }

    @Override
    public List<Customer> findAll() {
        return customerMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public List<Customer> findByPredicate(Predicate<Customer> predicate) {
        return customerMap.values().stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
