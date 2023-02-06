package java_cp2_javier_guerra.entities;

import java_cp2_javier_guerra.enums.BankAccountType;

import java.time.LocalDate;
import java.util.Set;
import java.util.HashSet;
import java.util.Objects;

public class BankAccount {

    private Long id;
    private Double amount = 0d;
    private BankAccountType type;
    private Double cost;
    private Double reward;
    private Set<String> currencies = new HashSet<>();
    private String userNif;
    private Customer customer;
    private Integer openingYear;
    private LocalDate creation;

    public BankAccount() {
    }

    public BankAccount(Long id, Double amount, BankAccountType type, Double cost, String userNif, Integer openingYear) {
        setId(id);
        setAmount(amount);
        setType(type);
        setCost(cost);
        setUserNif(userNif);
        setOpeningYear(openingYear);
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

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getUserNif() {
        return userNif;
    }

    public void setUserNif(String userNif) {
        this.userNif = userNif;
    }

    public Integer getOpeningYear() {
        return openingYear;
    }

    public void setOpeningYear(Integer openingYear) {
        this.openingYear = openingYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BankAccount that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return id + ": " + amount + ", " + type + ", " + cost + ", '" + userNif + "', " + openingYear;
    }
}
