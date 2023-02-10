package java_cp2_javier_guerra.entities;

import java_cp2_javier_guerra.entities.enums.BankAccountType;
import java_cp2_javier_guerra.entities.enums.CurrencyType;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.HashSet;
import java.util.Objects;

public class Account {

    private Long id;
    private Double amount = 0d;
    private BankAccountType type;
    private Set<CurrencyType> currencies = new HashSet<>();
    private Long idCustomer;
    private Long idCreationEmployee;
    private LocalDateTime creationDate = null;
    private LocalDateTime inactiveDate = null;
    private boolean active = true;

    public Account() {}

    public Account(Long id, Double amount, BankAccountType type, Set<CurrencyType> currencies, Long idCustomer, Long idCreationEmployee) {
        setId(id);
        setAmount(amount);
        setType(type);
        setCurrencies(currencies);
        setIdCustomer(idCustomer);
        setIdCreationEmployee(idCreationEmployee);
        setCreationDate(LocalDateTime.now());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void increaseAmount(Double amount) {
        if (amount > 0) this.amount += amount;
    }

    public void decrementAmount(Double amount) {
        if (amount > 0 && this.amount >= amount) this.amount -= amount;
    }

    public BankAccountType getType() {
        return type;
    }

    public void setType(BankAccountType type) {
        this.type = type;
    }

    public Set<CurrencyType> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Set<CurrencyType> currencies) {
        this.currencies = currencies;
    }

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Long getIdCreationEmployee() {
        return idCreationEmployee;
    }

    public void setIdCreationEmployee(Long idCreationEmployee) {
        this.idCreationEmployee = idCreationEmployee;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creation) {
        this.creationDate = creation;
    }

    public LocalDateTime getInactiveDate() {
        return inactiveDate;
    }

    public void setInactiveDate(LocalDateTime inactiveDate) {
        this.inactiveDate = inactiveDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "ID: " + id + ", ID usuario: " + idCustomer +
                ", Saldo: " + amount + ", Cuenta: " + type + ", Activa: " + (active ? "Sí" : "No");
    }
}
