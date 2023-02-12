package java_cp2_javier_guerra.repositories;

import java_cp2_javier_guerra.entities.Address;

import java.util.HashMap;
import java.util.Map;

public final class AddressRepository {

    private Map<Long, Address> addresses = new HashMap<>();

    public AddressRepository(Boolean data) {
        setAddresses(exampleAddresses(data));
    }

    public Map<Long, Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Map<Long, Address> addresses) {
        this.addresses = addresses;
    }

    /**
     * Genera una lista de direcciones de ejemplo o una lista vacía.
     * @param data true incluye datos de ejemplo, false no lo hace.
     * @return Lista de direcciones.
     */
    private Map<Long, Address> exampleAddresses(Boolean data) {
        if (data == null) data = false;

        Map<Long, Address> addresses = new HashMap<>();

        if (data) {
            var address1 = new Address(1L, "Dirección 1", "04341", "Madrid", "España");
            var address2 = new Address(2L, "Dirección 2", "04342", "Madrid", "España");
            var address3 = new Address(3L, "Dirección 3", "04343", "Barcelona", "España");
            var address4 = new Address(4L, "Dirección 4", "04344", "Barcelona", "España");
            var address5 = new Address(5L, "Dirección 5", "04345", "Barcelona", "España");

            addresses.putAll(Map.of(address1.getId(), address1, address2.getId(), address2, address3.getId(), address3, address4.getId(), address4, address5.getId(), address5));
        }

        return addresses;
    }
}
