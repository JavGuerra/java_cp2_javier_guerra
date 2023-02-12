package java_cp2_javier_guerra.services.implementations;

import java_cp2_javier_guerra.entities.Account;
import java_cp2_javier_guerra.entities.enums.BankAccountType;
import java_cp2_javier_guerra.entities.enums.CurrencyType;
import java_cp2_javier_guerra.repositories.AccountRepository;
import java_cp2_javier_guerra.services.IAccountService;

import java.util.*;

public class AccountServiceImpl implements IAccountService {

    private final Map<Long, Account> accounts = new AccountRepository(true).getAccounts();

    @Override
    public boolean accountExistById(Long id) {
        return accounts.containsKey(id);
    }

    @Override
    public Optional<Account> addAccount(Account account) {
        return Optional.ofNullable(accounts.put(account.getId(), account));
    }

    @Override
    public List<Account> getAllAccounts() {
        return new ArrayList<>(accounts.values());
    }

    @Override
    public Optional<Account> getAccountById(Long id) {
        return Optional.ofNullable(accounts.get(id));
    }

    @Override
    public Optional<Account> getAccountByCustomerId(Long id) {
        if (id > 0) {
            for (Account account : accounts.values())
                if (account.getIdCustomer().equals(id)) return Optional.of(account);
        }
        return Optional.empty();
    }

    @Override
    public List<Account> getAllAccountsByType(byte numType) {
        List<Account> listAccounts =  new ArrayList<>();
        if (numType >= 1 && numType <= BankAccountType.values().length) {
            BankAccountType type = BankAccountType.values()[--numType];
            for (Account account : accounts.values())
                if (account.getType() == type) listAccounts.add(account);
        }
      return listAccounts;
    }

    @Override
    public List<Account> getAllAccountsByCurrency(byte numType) {
        List<Account> listAccounts =  new ArrayList<>();
        if (numType >= 1 && numType <= CurrencyType.values().length) {
            CurrencyType type = CurrencyType.values()[--numType];
            for (Account account : accounts.values()) {
                for (CurrencyType currency : account.getCurrencies()) {
                    if (currency == type) {
                        listAccounts.add(account);
                        break;
                    }
                }
            }
        }
        return listAccounts;
    }

    public boolean currencyExist(Long id, byte numType) {
        boolean thereIsCurrency = false;
        if (id > 0 && numType >= 0 && numType <= CurrencyType.values().length - 1) {
            CurrencyType currencyType =  CurrencyType.values()[numType];
            Optional<Account> optAccount = getAccountById(id);
            if (optAccount.isPresent()) {
                Account account = optAccount.get();
                for (CurrencyType type : account.getCurrencies()) {
                    if (type == currencyType) {
                        thereIsCurrency = true;
                        break;
                    }
                }
            }
        }
        return thereIsCurrency;
    }

    @Override
    public boolean incrementAccountAmount(Long id, Double amount) {
        boolean incremented = false;
        if (amount > 0) {
            Optional<Account> optAccount = getAccountById(id);
            if (optAccount.isPresent()) {
                Account account = optAccount.get();
                account.increaseAmount(amount);
                incremented = true;
            }
        }
        return incremented;
    }

    @Override
    public boolean decrementAccountAmount(Long id, Double amount) {
        boolean decremented = false;
        if (amount > 0) {
            Optional<Account> optAccount = getAccountById(id);
            if (optAccount.isPresent()) {
                Account account = optAccount.get();
                if (account.getAmount() >= amount) {
                    account.decrementAmount(amount);
                    decremented = true;
                }
            }
        }
        return decremented;
    }

    @Override
    public boolean transferAccountAmount(Long id1, Long id2, Double amount) {
        boolean transferred = false;
        if (amount > 0) {
            Optional<Account> account1 = getAccountById(id1);
            Optional<Account> account2 = getAccountById(id1);
            if (account1.isPresent() && account2.isPresent() && account1.get().getAmount() >= amount)
                if (decrementAccountAmount(id1, amount)) transferred = incrementAccountAmount(id2, amount);
        }
        return transferred;
    }

    @Override
    public boolean deleteAccount(Long id) {
        return accounts.remove(id) != null;
    }
}
