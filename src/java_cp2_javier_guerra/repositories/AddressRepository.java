package java_cp2_javier_guerra.repositories;

import java_cp2_javier_guerra.entities.Address;

import java.util.HashMap;
import java.util.Map;

public class AddressRepository {

    public static Map<Long, Address> exampleAddresses() {

        var address1 = new Address(1L, "Dirección 1", "04341", "Madrid", "España");
        var address2 = new Address(2L, "Dirección 2", "04342", "Madrid", "España");
        var address3 = new Address(3L, "Dirección 3", "04343", "Barcelona", "España");
        var address4 = new Address(4L, "Dirección 4", "04344", "Barcelona", "España");
        var address5 = new Address(5L, "Dirección 5", "04345", "Barcelona", "España");

        return new HashMap<>
                (Map.of(address1.getId(), address1, address2.getId(), address2, address3.getId(), address3, address4.getId(), address4, address5.getId(), address5));
    }
}
