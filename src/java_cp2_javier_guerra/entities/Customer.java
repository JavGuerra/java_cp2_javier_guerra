package java_cp2_javier_guerra.entities;

import java.time.LocalDateTime;
import java.util.Objects;

public class Customer {

    private Long id;
    private String name;
    private String dniNie;
    private Long addressId;
    private String phone;
    private String email;
    private Boolean active = true;
    private LocalDateTime openingAccountDate = null;
    private LocalDateTime closingAccountDate = null;

    Customer() {}

    public Customer(Long id, String name, String dniNie, Long addressId, String phone, String email) {
        setId(id);
        setName(name);
        setDniNie(dniNie);
        setAddressId(addressId);
        setPhone(phone);
        setEmail(email);
        setOpeningAccountDate(LocalDateTime.now());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDniNie() {
        return dniNie;
    }

    public void setDniNie(String dniNie) {
        this.dniNie = dniNie;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDateTime getOpeningAccountDate() {
        return openingAccountDate;
    }

    public void setOpeningAccountDate(LocalDateTime openingAccountDate) {
        this.openingAccountDate = openingAccountDate;
    }

    public LocalDateTime getClosingAccountDate() {
        return closingAccountDate;
    }

    public void setClosingAccountDate(LocalDateTime closingAccountDate) {
        this.closingAccountDate = closingAccountDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id.equals(customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Cliente ID: " + id + ", Nombre: " + name + ", NIF/NIE: " + dniNie + ", Activo: " + (active ? "Sí" : "No");
    }
}
