package java_cp2_javier_guerra.entities;

import java.time.LocalDateTime;
import java.util.Objects;

public class Customer {

    private Long id;
    private String name;
    private String nif;
    private Address address;
    private Boolean active = true;
    private LocalDateTime openingAccountDate = null;
    private LocalDateTime closingAccountDate = null;

    Customer() {}

    public Customer(Long id, String name, String nif, Address address) {
        setId(id);
        setName(name);
        setNif(nif);
        setAddress(address);
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

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nif='" + nif + '\'' +
                ", address=" + address +
                ", active=" + active +
                ", openingAccountDate=" + openingAccountDate +
                ", closingAccountDate=" + closingAccountDate +
                '}';
    }
}
