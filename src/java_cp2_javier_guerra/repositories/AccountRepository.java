package java_cp2_javier_guerra.repositories;

import java_cp2_javier_guerra.entities.Account;
import java_cp2_javier_guerra.entities.enums.AccountType;
import java_cp2_javier_guerra.entities.enums.CurrencyType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class AccountRepository {

    private Map<Long, Account> accounts = new HashMap<>();

    public AccountRepository(Boolean data) {
        setAccounts(exampleAccounts(data));
    }

    public Map<Long, Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Map<Long, Account> accounts) {
        this.accounts = accounts;
    }

    /**
     * Genera una lista de cuentas bancarias de ejemplo o una lista vacía.
     * @param data true incluye datos de ejemplo, false no lo hace.
     * @return Lista de cuentas bancarias.
     */
    private Map<Long, Account> exampleAccounts(Boolean data) {
        if (data == null) data = false;

        Map<Long, Account> accounts = new HashMap<>();

        if (data) {
            var currencies1 = new HashSet<>(Set.of(CurrencyType.EUR, CurrencyType.USD));
            var currencies2 = new HashSet<>(Set.of(CurrencyType.JPY, CurrencyType.USD));
            var currencies3 = new HashSet<>(Set.of(CurrencyType.CZK, CurrencyType.CHF, CurrencyType.RUB));

            var account1 = new Account(1L, 50000d, AccountType.BUSINESS, currencies1, 1L, 1L);
            var account2 = new Account(2L, 5000d, AccountType.INVESTMENT, currencies2, 2L, 1L);
            var account3 = new Account(3L, 10000d, AccountType.BUSINESS, currencies3, 3L, 1L);

            accounts.putAll(Map.of(account1.getId(), account1, account2.getId(), account2, account3.getId(), account3));
        }

        return accounts;
    }
}
