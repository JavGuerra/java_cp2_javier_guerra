package java_cp2_javier_guerra.implementations;

import java_cp2_javier_guerra.entities.Customer;
import java_cp2_javier_guerra.interfaces.ICustomerService;

import java.util.*;

public class CustomerServiceImpl implements ICustomerService {

    private final Map<Long, Customer> customers = new HashMap<>();

    @Override
    public void addCustomer(Customer customer) {
        // TODO comprobar que se guarda
        customers.put(customer.getId(), customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public Optional<Customer> findCustomerByNif(String nif) {
        if (nif != null && !nif.equals("")) {
            for (Customer customer : customers.values())
                if (customer.getNif().equals(nif)) return Optional.of(customer);
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteCustomer(Long id) {
        return customers.remove(id) != null;
    }
}
