package java_cp2_javier_guerra.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Bank {

    private Long id;
    private String name;
    private String nif;
    private Map<String, Object> services = new HashMap<>();

    public Bank() {}

    public Bank(Long id, String name, String nif) {
        setId(id);
        setName(name);
        setNif(nif);
    }

    public Bank(Long id, String name, String nif, Map<String, Object> services) {
        setId(id);
        setName(name);
        setNif(nif);
        setServices(services);
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

    public Map<String, Object> getServices() {
        return services;
    }

    public void setServices(Map<String, Object> services) {
        this.services = services;
    }

    public Object getService(String service) {
        return services.get(service);
    }

    public void setService(String name, Object service) {
        services.put(name, service);
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
        return "ID: " + id + ", «" + name + "», " + nif;
    }
}
