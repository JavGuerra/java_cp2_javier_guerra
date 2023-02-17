package java_cp2_javier_guerra.services;

import java_cp2_javier_guerra.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {

    /**
     * Añade un cliente a la lista de clientes.
     * @param customer cliente a añadir.
     * @return opcionalmente, el mismo cliente.
     */
    Optional<Customer> addCustomer(Customer customer);

    /**
     * Obtiene todos los clientes.
     * @return lista de clientes.
     */
    List<Customer> getAllCustomers();

    /**
     * Obtiene un cliente por su ID.
     * @param id del cliente.
     * @return opcionalmente, cliente o null.
     */
    Optional<Customer> getCustomerById(long id);

    /**
     * Obtiene un cliente por su NIF.
     * @param nif del cliente.
     * @return opcionalmente, cliente o null.
     */
    Optional<Customer> getCustomerByNif(String nif);

    /**
     * Borra un cliente de la lista.
     * @param id del cliente.
     * @return true si se borró el cliente o false en caso contrario.
     */
    boolean deleteCustomer(long id);
}
