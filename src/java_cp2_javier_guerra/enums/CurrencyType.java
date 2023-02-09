package java_cp2_javier_guerra.enums;

public enum CurrencyType {
    EUR("Euro"),
    USD("Dollar"),
    GBP("Libra esterlina"),
    RUB("Rublo"),
    CHF("Franco suizo"),
    JPY("Yen"),
    SGD("Dollar Singapur"),
    CZK("Corona checa");

    private String name;

    CurrencyType(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
