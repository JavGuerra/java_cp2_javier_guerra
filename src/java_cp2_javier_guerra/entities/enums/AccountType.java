package java_cp2_javier_guerra.entities.enums;

public enum AccountType {

    SAVING("Ahorro", 1d, 10d),
    BUSINESS("Empresa", 2d, 10d),
    INVESTMENT("Inversión", 3d, 10d);

    private String name;
    private double cost;
    private double reward;

    AccountType(String name, double cost, double reward) {
        setName(name);
        setCost(cost);
        setReward(reward);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getReward() {
        return reward;
    }

    public void setReward(double reward) {
        this.reward = reward;
    }

    /**
     * Devuelve una lista numerada de los tipos de cuenta disponibles.
     * @return Lista numera de tipos de cuenta.
     */
    public static String getBankAccountTypeList() {
        StringBuilder str = new StringBuilder();
        for (AccountType type : AccountType.values())
            str.append(" (").append(type.ordinal() + 1).append(") ").append(type.getName());
        return str.toString();
    }

    /**
     * Devuelve una lista numerada de los tipos de cuenta disponibles excepto el indicado.
     * @param accountType tipo indicado.
     * @return Lista numera de tipos de cuenta.
     */
    public static String getBankAccountTypeListExceptOne(AccountType accountType) {
        StringBuilder str = new StringBuilder();
        if (accountType != null) {
            for (AccountType type : AccountType.values())
                if (!type.getName().equals(accountType.getName()))
                    str.append(" (").append(type.ordinal() + 1).append(") ").append(type.getName());
        }
        return str.toString();
    }
}
