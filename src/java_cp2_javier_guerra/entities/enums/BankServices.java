package java_cp2_javier_guerra.entities.enums;

public enum BankServices {
    LOANS("préstamos"), MORTGAGES("hipotecas"), RECEIPTS("recibos"), ACCOUNTS("cuentas"),
    CARDS("tarjetas"), CASHIERS("cajeros"), TRANSFERS("transferencias"), DEPOSITS("depósitos"),
    INVESTMENTS("inversiones"), GUARANTEES("avales");

    private String name;

    BankServices(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
