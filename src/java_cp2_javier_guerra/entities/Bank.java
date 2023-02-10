package java_cp2_javier_guerra.entities;

import java_cp2_javier_guerra.entities.enums.BankServices;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class Bank {

    private Long id;
    private String name;
    private String nif;
    private Long addressId;
    private Set<BankServices> bankServices = new HashSet<>();

    public Bank() {}

    public Bank(Long id, String name, String nif, Long addressId) {
        setId(id);
        setName(name);
        setNif(nif);
        setAddressId(addressId);
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

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Set<BankServices> getBankServices() {
        return bankServices;
    }

    public void setBankServices(Set<BankServices> bankServices) {
        this.bankServices = bankServices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return id.equals(bank.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Banco «" + name + "», " + nif + " Servicios:");
        for (BankServices bankService : bankServices) str.append(" ").append(bankService.getName());
        return str.toString();
    }
}
