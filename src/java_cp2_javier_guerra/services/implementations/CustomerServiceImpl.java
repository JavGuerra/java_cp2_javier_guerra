package java_cp2_javier_guerra.services.implementations;

import java_cp2_javier_guerra.entities.Customer;
import java_cp2_javier_guerra.repositories.CustomerRepository;
import java_cp2_javier_guerra.services.ICustomerService;

import java.util.*;

public class CustomerServiceImpl implements ICustomerService {

    private final Map<Long, Customer> customers = new CustomerRepository(true).getCustomers();

    @Override
    public Optional<Customer> addCustomer(Customer customer) {
        return Optional.ofNullable(customers.put(customer.getId(), customer));
    }

    @Override
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public Optional<Customer> getCustomerById(long id) {
        if (id >= 0) return Optional.ofNullable(customers.get(id));
        return Optional.empty();
    }

    @Override
    public Optional<Customer> getCustomerByNif(String nif) {
        if (nif != null && !nif.equals("")) {
            for (Customer customer : customers.values())
                if (customer.getNif().equals(nif)) return Optional.of(customer);
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteCustomer(long id) {
        return customers.remove(id) != null;
    }
}
