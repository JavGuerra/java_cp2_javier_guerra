package java_cp2_javier_guerra.implementations;

import java_cp2_javier_guerra.entities.Address;
import java_cp2_javier_guerra.interfaces.IAddressService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressServiceImpl implements IAddressService {

    private final Map<Long, Address> addresses = new HashMap<>();

    @Override
    public void addAddress(Address address) {
        // TODO comprobar que se guarda
        addresses.put(address.getId(), address);
    }

    @Override
    public List<Address> getAllAddress() {
        return new ArrayList<>(addresses.values());
    }

    @Override
    public boolean deleteAddress(Long id) {
        return addresses.remove(id) != null;
    }
}
