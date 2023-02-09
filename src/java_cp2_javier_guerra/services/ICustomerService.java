package java_cp2_javier_guerra.services;

import java_cp2_javier_guerra.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {

    void addCustomer(Customer customer);

    List<Customer> getAllCustomers();

    Optional<Customer> findCustomerByNif(String nif);

    boolean deleteCustomer(Long id);
}
