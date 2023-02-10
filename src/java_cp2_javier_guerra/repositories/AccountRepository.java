package java_cp2_javier_guerra.repositories;

import java_cp2_javier_guerra.entities.Account;
import java_cp2_javier_guerra.entities.BankAccountType;
import java_cp2_javier_guerra.entities.CurrencyType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AccountRepository {

    public static Map<Long, Account> exampleAccounts() {

        var currencies1 = new HashSet<>(Set.of(CurrencyType.EUR, CurrencyType.USD));
        var currencies2 = new HashSet<>(Set.of(CurrencyType.JPY, CurrencyType.USD));
        var currencies3 = new HashSet<>(Set.of(CurrencyType.CZK, CurrencyType.CHF, CurrencyType.RUB));

        var account1 = new Account(1L, 50000d, BankAccountType.BUSINESS, currencies1, 1L, 1L);
        var account2 = new Account(2L, 5000d, BankAccountType.INVESTMENT, currencies2, 2L, 1L);
        var account3 = new Account(3L, 10000d, BankAccountType.BUSINESS, currencies3, 3L, 1L);

        return new HashMap<>
                (Map.of(account1.getId(), account1, account2.getId(), account2, account3.getId(), account3));
    }
}
