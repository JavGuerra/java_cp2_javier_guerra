package java_cp2_javier_guerra.interfaces;

import java_cp2_javier_guerra.entities.Address;

import java.util.List;

public interface IAddressService {

    void addAddress(Address address);

    List<Address> getAllAddress();

    boolean deleteAddress(Long id);
}
