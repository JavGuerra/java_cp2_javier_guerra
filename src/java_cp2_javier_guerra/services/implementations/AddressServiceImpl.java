package java_cp2_javier_guerra.services.implementations;

import java_cp2_javier_guerra.entities.Address;
import java_cp2_javier_guerra.repositories.AddressRepository;
import java_cp2_javier_guerra.services.IAddressService;

import java.util.*;

public class AddressServiceImpl implements IAddressService {

    private final Map<Long, Address> addresses = new AddressRepository(true).getAddresses();

    @Override
    public Optional<Address> addAddress(Address address) {
        return Optional.ofNullable(addresses.put(address.getId(), address));
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
