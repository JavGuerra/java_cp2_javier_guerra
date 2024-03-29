package java_cp2_javier_guerra.entities.enums;

public enum CurrencyType {
    EUR("Euro", 0.93d),
    USD("Dollar", 0d),
    GBP("Libra esterlina", 0.82d),
    RUB("Rublo", 72.96d),
    CHF("Franco suizo", 0.92d),
    JPY("Yen", 130.98d),
    SGD("Dollar Singapur", 1.32d),
    CZK("Corona checa", 22.10d);

    private String name;
    private Double usdExchange; // 1 USD = usdExchange

    CurrencyType(String name, Double usdExchange) {
        setName(name);
        setUsdExchange(usdExchange);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getUsdExchange() {
        return usdExchange;
    }

    public void setUsdExchange(Double usdExchange) {
        this.usdExchange = usdExchange;
    }

    /**
     * Devuelve una lista numerada de los tipos de moneda disponibles.
     * @return Lista numera de tipos de moneda.
     */
    public static String getCurrencyTypeList() {
        StringBuilder str = new StringBuilder();
        for (CurrencyType type : CurrencyType.values())
            str.append(" (").append(type.ordinal() + 1).append(") ").append(type);
        return str.toString();
    }
}
