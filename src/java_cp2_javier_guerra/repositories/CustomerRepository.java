package java_cp2_javier_guerra.repositories;

import java_cp2_javier_guerra.entities.Customer;

import java.util.HashMap;
import java.util.Map;

public final class CustomerRepository {

    private Map<Long, Customer> customers = new HashMap<>();

    public CustomerRepository(Boolean data) {
        setCustomers(exampleCustomers(data));
    }

    public Map<Long, Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Map<Long, Customer> customers) {
        this.customers = customers;
    }

    /**
     * Genera una lista de clientes de ejemplo o una lista vacía.
     * @param data true incluye datos de ejemplo, false no lo hace.
     * @return Lista de clientes.
     */
    private Map<Long, Customer> exampleCustomers(Boolean data) {
        if (data == null) data = false;

        Map<Long, Customer> customers = new HashMap<>();

        if (data) {
            var customer1 = new Customer(1L, "Cliente 1", "12345678A", 1L, "+34 123 456 001", "cliente1@email.com");
            var customer2 = new Customer(2L, "Cliente 2", "12345678B", 2L, "+34 123 456 002", "cliente2@email.com");
            var customer3 = new Customer(3L, "Cliente 3", "12345678C", 3L, "+34 123 456 003", "cliente3@email.com");

            return new HashMap<>
                    (Map.of(customer1.getId(), customer1, customer2.getId(), customer2, customer3.getId(), customer3));
        }

        return customers;
    }
}
