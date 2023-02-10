package java_cp2_javier_guerra.repositories;

import java_cp2_javier_guerra.entities.Customer;

import java.util.HashMap;
import java.util.Map;

public final class CustomerRepository {

    private Map<Long, Customer> customers = new HashMap<>();

    public CustomerRepository() {
        setCustomers(exampleCustomers());
    }

    public Map<Long, Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Map<Long, Customer> customers) {
        this.customers = customers;
    }

    private Map<Long, Customer> exampleCustomers() {
        var customer1 = new Customer(1L, "Cliente 1", "12345678A", 1L);
        var customer2 = new Customer(2L, "Cliente 2", "12345678B", 2L);
        var customer3 = new Customer(3L, "Cliente 3", "12345678C", 3L);

        return new HashMap<>
                (Map.of(customer1.getId(), customer1, customer2.getId(), customer2, customer3.getId(), customer3));
    }
}
