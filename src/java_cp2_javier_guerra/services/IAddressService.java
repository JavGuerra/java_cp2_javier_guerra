package java_cp2_javier_guerra.services;

import java_cp2_javier_guerra.entities.Address;

import java.util.List;
import java.util.Optional;

public interface IAddressService {

    /**
     * Añade una dirección a la lista.
     * @param address dirección a añadir.
     * @return la dirección añadida.
     */
    Optional<Address> addAddress(Address address);

    /**
     * Obtiene la lista de direcciones.
     * @return la lista de direcciones.
     */
    List<Address> getAllAddress();

    /**
     * Borra una dirección indicada por el id.
     * @param id de la dirección a borrar.
     * @return true si se borró o false en caso contrario.
     */
    boolean deleteAddress(Long id);
}
