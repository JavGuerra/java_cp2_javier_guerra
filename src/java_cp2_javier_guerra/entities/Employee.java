package java_cp2_javier_guerra.entities;

import java.time.LocalDateTime;
import java.util.Objects;

public class Employee {

    private Long id;
    private String name;
    private String nif;
    private Long addressId;
    private Boolean active = true;
    private LocalDateTime entryDate = null;
    private LocalDateTime endDate = null;

    Employee() {}

    public Employee(Long id, String name, String nif, Long addressId) {
        setId(id);
        setName(name);
        setNif(nif);
        setAddressId(addressId);
        setEntryDate(LocalDateTime.now());
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

    public Long getAddressId() {
        return addressId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id.equals(employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nif='" + nif + '\'' +
                '}';
    }
}
